package ci.digitalacademy.com;

import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.model.User;
import ci.digitalacademy.com.repository.RoleRepository;
import ci.digitalacademy.com.repository.UserRepository;
import ci.digitalacademy.com.security.AuthorityConstants;
import ci.digitalacademy.com.service.IncomeService;
import ci.digitalacademy.com.service.dto.IncomeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RequiredArgsConstructor
public class BrobroliApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IncomeService incomeService;

    public static void main(String[] args) {
        SpringApplication.run(BrobroliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IncomeDTO incomeDTO = new IncomeDTO();
        List<Role> all = roleRepository.findAll();
        List<Role> user = roleRepository.findAll();
        List<IncomeDTO> incomeDTOS = incomeService.findAll();
        if (all.isEmpty()){
            Role role = new Role();
            Role role2 = new Role();
            Role role3 = new Role();
            role3.setRole(AuthorityConstants.ADMIN);
            role2.setRole(AuthorityConstants.CUSTOMER);
            role.setRole(AuthorityConstants.PROVIDER);
            roleRepository.save(role);
            roleRepository.save(role2);
            roleRepository.save(role3);
        }
        Integer integer = userRepository.countAllByRoleRole(AuthorityConstants.ADMIN);
        if (integer==0){
            User user1 = new User();
            user1.setUserName("admin");
            user1.setRole(roleRepository.findByRole(AuthorityConstants.ADMIN).get());
            user1.setPassword(bCryptPasswordEncoder.encode("delmasbrou"));
            user1.setActif(true);
            userRepository.save(user1);
            }
        if (incomeDTOS.isEmpty()){
            incomeDTO.setRevenu(0f);
            incomeService.save(incomeDTO);
        }
        }
}
