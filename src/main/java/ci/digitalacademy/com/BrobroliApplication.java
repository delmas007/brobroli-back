package ci.digitalacademy.com;

import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.repository.RoleRepository;
import ci.digitalacademy.com.security.AuthorityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BrobroliApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;


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
    }

}
