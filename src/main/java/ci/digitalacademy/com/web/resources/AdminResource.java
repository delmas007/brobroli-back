package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.AdminService;
import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.ServiceService;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.NumberUserDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminResource {

    private final AdminService adminService;
    private final ServiceService serviceService;
    private final ProviderService providerService;
    private final CustomerService customerService;

    @GetMapping
    public NumberUserDTO numberListUser(){
        log.debug("REST request to find all");
        return adminService.numberListUser();
    }
    @PutMapping("/service/valid/{seriveId}")
    public void validSercice(@PathVariable long seriveId) {
        log.debug("REST, Request to accept Service : {}", seriveId);
        adminService.valid(seriveId);
    }
    @PutMapping("/service/reject/{seriveId}")
    public void rejectService(@PathVariable long seriveId) {
        log.debug("REST, Request to reject Service : {}", seriveId);
        adminService.reject(seriveId);
    }
    @GetMapping("/service")
    public List<ServiceDTO> findAllServices(){
        log.debug("REST request to find all");
        return serviceService.findAll();
    }
    @GetMapping("/providers")
    public List<ProviderDTO> findAllProviders(){
        log.debug("REST request to find all");
        return providerService.findAll();
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomer(){
        log.debug("REST request to find all");
        return customerService.findAll();
    }

    @PutMapping("/provider/activate/{providerId}")
    public void activateProvider(@PathVariable long providerId) {
        log.debug("REST, Request to activate Provider : {}", providerId);
        providerService.activateProvider(providerId);
    }

    @PutMapping("/provider/deactivate/{providerId}")
    public void deactivateProvider(@PathVariable long providerId) {
        log.debug("REST, Request to deactivate Provider : {}", providerId);
        providerService.deactivateProvider(providerId);
    }

    @PutMapping("/customer/activate/{customerId}")
    public void activateCustomer(@PathVariable long customerId) {
        log.debug("REST, Request to activate Customer : {}", customerId);
        customerService.activateCustomer(customerId);
    }

    @PutMapping("/customer/deactivate/{customerId}")
    public void deactivateCustomer(@PathVariable long customerId) {
        log.debug("REST, Request to deactivate Customer : {}", customerId);
        customerService.deactivateCustomer(customerId);
    }
}
