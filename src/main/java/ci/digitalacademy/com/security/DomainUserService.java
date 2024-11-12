package ci.digitalacademy.com.security;


import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.model.User;
import ci.digitalacademy.com.repository.UserRepository;
import ci.digitalacademy.com.web.exception.EntityNotFoundException;
import ci.digitalacademy.com.web.exception.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUserName(username);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("Utilisateur inexistant", ErrorCodes.UTILISATEUR_PAS_TROUVER);
        }
        if (!user.get().getActif()) {
            throw new EntityNotFoundException("Utilisateur pas actif, veuillez contacter les administrateurs",ErrorCodes.UTILISATEUR_NON_ACTIF);
        }

        Role role = user.get().getRole();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("SCOPE_"+role.getRole());
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(grantedAuthority);

        return user.map(userRecover -> org.springframework.security.core.userdetails.User.builder()
                .username(userRecover.getUserName())
                .password(userRecover.getPassword())
                .authorities(grantedAuthorities)
//                .disabled(!userRecover.getActive())
                .build()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
