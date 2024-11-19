package ci.digitalacademy.com.service.imp;

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
    private final IncomeService incomeService;

    @Override
    public NumberUserDTO numberListUser() {
        NumberUserDTO numberUserDTO = new NumberUserDTO();
        List<UserDTO> listUser = userService.findAll();
        List<ProviderDTO> listProvider = providerService.findAll();
        List<CustomerDTO> listCustomer = customerService.findAll();
        numberUserDTO.setNumberProvider(listProvider.size());
        numberUserDTO.setNumberCustomer(listCustomer.size());
        numberUserDTO.setNumberUser(numberUserDTO.getNumberCustomer()+ numberUserDTO.getNumberProvider());
        if (numberUserDTO.getNumberUser() != 0) {
            numberUserDTO.setPourcentageCustomer((numberUserDTO.getNumberCustomer() * 100) / numberUserDTO.getNumberUser());
            numberUserDTO.setPourcentageProvider((numberUserDTO.getNumberProvider() * 100) / numberUserDTO.getNumberUser());
        } else {
            numberUserDTO.setPourcentageCustomer(0);
            numberUserDTO.setPourcentageProvider(0);
        }
        numberUserDTO.setNumberServiceEnAttente(serviceService.findAll().stream().map(ServiceDTO::getStatus).filter(ServiceStatus.ON_HOLD::equals).count());
        numberUserDTO.setListProvider(listProvider);
        numberUserDTO.setListCustomer(listCustomer);
        numberUserDTO.setRevenue(incomeService.findAll().stream().map(IncomeDTO::getRevenu).reduce(0.0F, Float::sum));
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
