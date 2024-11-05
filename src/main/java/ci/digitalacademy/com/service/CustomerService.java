package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.FileCustomerDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public interface CustomerService {
    //Enregitrer un client
    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws IOException;

    CustomerDTO save(CustomerDTO customerDTO);

    //La liste des Forums
    List<CustomerDTO> findAllcustomer();

    CustomerDTO updateCustomer(FileCustomerDTO customerDTO) ;

    // Modification d'un client existant
    CustomerDTO updateCustomer(Long id, FileCustomerDTO customerDTO)throws IOException;

    // Recherche d'un client par son ID
    Optional<CustomerDTO> findOneCustomer(Long id);
    Optional<CustomerDTO> findByUserId(Long id);

    // Recherche d'un client par son slug
    Optional<CustomerDTO> getCustomerBySlug(String slug);


}
