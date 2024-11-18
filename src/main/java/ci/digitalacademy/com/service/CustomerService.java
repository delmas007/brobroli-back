package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.FileCustomerDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public interface CustomerService {
    CustomerDTO saveCustomer(FileCustomerDTO customerDTO) throws IOException;

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO uploadCustumerImage(Long id, FileCustomerDTO fileCustomerDTO) throws IOException;

    List<CustomerDTO> findAllcustomer();

    CustomerDTO updateCustomer(FileCustomerDTO customerDTO) ;

    CustomerDTO updateCustomer(Long id, FileCustomerDTO customerDTO)throws IOException;

    Optional<CustomerDTO> findOneCustomer(Long id);
    Optional<CustomerDTO> findByUserId(Long id);

    Optional<CustomerDTO> getCustomerBySlug(String slug);

}
