package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.service.AdminService;
import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.UserService;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.NumberUserDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final CustomerService customerService;
    private final ProviderService providerService;
    @Override
    public NumberUserDTO numberListUser() {
        NumberUserDTO numberUserDTO = new NumberUserDTO();
        List<UserDTO> listUser = userService.findAll();
        List<ProviderDTO> listProvider = providerService.findAll();
        List<CustomerDTO> listCustomer = customerService.findAllcustomer();
        numberUserDTO.setNumberUser(listUser.size());
        numberUserDTO.setNumberProvider(listProvider.size());
        numberUserDTO.setNumberCustomer(listCustomer.size());
        numberUserDTO.setListProvider(listProvider);
        numberUserDTO.setListCustomer(listCustomer);
        return numberUserDTO;
    }
}
