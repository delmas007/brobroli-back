package ci.digitalacademy.com;

import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.repository.RoleRepository;
import ci.digitalacademy.com.repository.UserRepository;
import ci.digitalacademy.com.security.AuthorityConstants;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.UserService;
import ci.digitalacademy.com.service.dto.FileProviderDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BrobroliApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final ProviderService providerService;


    public static void main(String[] args) {
        SpringApplication.run(BrobroliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> all = roleRepository.findAll();
        if (all.isEmpty()){
            Role role = new Role();
            Role role2 = new Role();
            role2.setRole(AuthorityConstants.CUSTOMER);
            role.setRole(AuthorityConstants.PROVIDER);
            roleRepository.save(role);
            roleRepository.save(role2);
        }

        UserDTO user = new UserDTO();
        user.setUserName("Delon");
        user.setPassword("delon");
        user.setRememberMe(true);

        FileProviderDTO provider = new FileProviderDTO();
        provider.setFirstName("sidik");
        provider.setEmail("soum@gmail.com");
        provider.setCity("Gagnoa");
        provider.setUser(user);
        providerService.saveProvider(provider);

    }

}
