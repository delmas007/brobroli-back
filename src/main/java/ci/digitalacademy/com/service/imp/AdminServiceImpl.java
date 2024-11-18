package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.enume.CollaborationStatus;
import ci.digitalacademy.com.model.enume.CustomerStatusService;
import ci.digitalacademy.com.model.enume.ProviderStatusService;
import ci.digitalacademy.com.model.enume.ServiceStatus;
import ci.digitalacademy.com.service.*;
import ci.digitalacademy.com.service.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final CustomerService customerService;
    private final ProviderService providerService;
    private final ServiceService serviceService;
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
        List<ProviderDTO> listProviders = new ArrayList<>();
        listProvider.forEach(providerDTO -> {
            if (providerDTO.getService() != null){
                listProviders.add(providerDTO) ;
            }
        });
        numberUserDTO.setNumberProviderWithService(listProviders.size());
        return numberUserDTO;
    }

    @Override
    public void valid(Long seriveId) {
        log.debug("Request to accept Service");
        Optional<ServiceDTO> serviceDTO = serviceService.findOneById(seriveId);
        if (serviceDTO.isPresent()){
            ServiceDTO service = serviceDTO.get();
            service.setStatus(ServiceStatus.VALID);
            serviceService.save(service);
        }

    }

    @Override
    public void reject(Long seriveId) {
        Optional<ServiceDTO> serviceDTO = serviceService.findOneById(seriveId);
        if (serviceDTO.isPresent()){
            ServiceDTO service = serviceDTO.get();
            service.setStatus(ServiceStatus.REFUSE);
            serviceService.save(service);
        }

    }
}
