package ci.digitalacademy.com.web.resources;


import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.UserService;
import ci.digitalacademy.com.service.dto.*;
import ci.digitalacademy.com.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/authe")
@RequiredArgsConstructor
public class AuthenticateResource {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ProviderService providerService;
    private final CustomerService customerService;
    private final UserService userService;

    @Value("${security.authentication.jwt.token-validity-in-seconds:0}")
    private long tokenValidityInSeconds;

    @Value("${security.authentication.jwt.token-validity-in-seconds-for-remember-me:0}")
    private long tokenValidityInSecondsForRememberMe;

    private final JwtEncoder jwtEncoder;

    @PostMapping("/authenticate")
    public JWTTokenDTO authorize(@RequestBody UserDTO login) {
        UserDTO byUserName = userService.getByUserName(login.getUserName());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUserName(),
                login.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = createToken(authentication, login.getRememberMe(),byUserName);
        return new JWTTokenDTO(jwt);
    }
    public String createToken(Authentication authentication, boolean rememberMe, UserDTO userDTO) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity;
        if (rememberMe) {
            validity = now.plus(this.tokenValidityInSecondsForRememberMe, ChronoUnit.SECONDS);
        } else {
            validity = now.plus(this.tokenValidityInSeconds, ChronoUnit.SECONDS);
        }

        if (authorities.contains("SCOPE_PROVIDER")){
            Optional<ProviderDTO> byUserId = providerService.findByUserId(userDTO.getId());
            if (byUserId.isPresent()){
                ProviderDTO providerDTO = byUserId.get();
                JwtClaimsSet claims = JwtClaimsSet.builder()
                        .issuedAt(now)
                        .expiresAt(validity)
                        .subject(authentication.getName())
                        .claim(SecurityUtils.AUTHORITIES_KEY, authorities)
                        .claim("id",providerDTO.getId())
                        .build();
                JwsHeader jwsHeader = JwsHeader.with(SecurityUtils.JWT_ALGORITHM).build();
                return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
            }else {
                return null;
            }
        }
        else if (authorities.contains("SCOPE_CUSTOMER")){
            Optional<CustomerDTO> byUserId = customerService.findByUserId(userDTO.getId());
            if (byUserId.isPresent()){
                CustomerDTO customerDTO = byUserId.get();
                JwtClaimsSet claims = JwtClaimsSet.builder()
                        .issuedAt(now)
                        .expiresAt(validity)
                        .subject(authentication.getName())
                        .claim(SecurityUtils.AUTHORITIES_KEY, authorities)
                        .claim("id",customerDTO.getId())
                        .build();
                JwsHeader jwsHeader = JwsHeader.with(SecurityUtils.JWT_ALGORITHM).build();
                return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
            }
        }
        else if (authorities.contains("SCOPE_ADMIN")){
            UserDTO byUserName = userService.getByUserName(authentication.getName());
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuedAt(now)
                    .expiresAt(validity)
                    .subject(authentication.getName())
                    .claim(SecurityUtils.AUTHORITIES_KEY, authorities)
                    .claim("id",byUserName.getId())
                    .build();
            JwsHeader jwsHeader = JwsHeader.with(SecurityUtils.JWT_ALGORITHM).build();
            return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
        }
        return null;
    }

    @PostMapping("/providers")
    public ResponseEntity<ProviderDTO> saveProvider(@ModelAttribute FileProviderDTO fileProviderDTO) throws IOException {
        log.debug("REST request to save provider: {}", fileProviderDTO);
        ProviderDTO provider = providerService.saveProvider(fileProviderDTO);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }


    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> saveCustumer(@ModelAttribute FileCustomerDTO fileCustomerDTO) throws IOException {
        log.debug("REST request to save provider: {}", fileCustomerDTO);
        return new ResponseEntity<>(customerService.saveCustomer(fileCustomerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        log.debug("REST request to get one by id: {}", id);
        return new ResponseEntity<>(providerService.findOneById(id),HttpStatus.OK );
    }
}

