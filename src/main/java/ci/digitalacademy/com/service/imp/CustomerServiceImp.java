package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.repository.CustomerRepository;
import ci.digitalacademy.com.repository.RoleRepository;
import ci.digitalacademy.com.security.AuthorityConstants;
import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.FiltreStorageService;
import ci.digitalacademy.com.service.ValidationService;
import ci.digitalacademy.com.service.dto.*;
import ci.digitalacademy.com.service.mapper.CustomerMapper;
import ci.digitalacademy.com.service.mapper.RoleMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import ci.digitalacademy.com.web.exception.EntityNotFoundException;
import ci.digitalacademy.com.web.exception.ErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final FiltreStorageService filtreStorageService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidationService validationService;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public CustomerDTO saveCustomer(FileCustomerDTO fileCustomerDTO) throws IOException {
        log.debug("Saving new customer: {}", fileCustomerDTO);
        RoleDTO role2 = new RoleDTO();
        role2.setRole(AuthorityConstants.CUSTOMER);
        if (fileCustomerDTO.getUser() != null){
            fileCustomerDTO.getUser().setRole(role2);
            fileCustomerDTO.getUser().setPassword(bCryptPasswordEncoder.encode(fileCustomerDTO.getUser().getPassword()));
        }
        fileCustomerDTO.setCreateAt(LocalDate.now());
        fileCustomerDTO.setSlug(SlugifyUtils.generate(fileCustomerDTO.getFirstName()));
        if (fileCustomerDTO.getFileurlImage() != null && !fileCustomerDTO.getFileurlImage().isEmpty()) {
            String imageUrl = filtreStorageService.upload(fileCustomerDTO.getFileurlImage());
            fileCustomerDTO.setUrlProfil(imageUrl);
        }
        Customer customer = customerMapper.toEntity(fileCustomerDTO );
        customer = repository.save(customer);
        CustomerDTO customerDTO1 = customerMapper.fromEntity(customer);
        validationService.registerCustomer(customerDTO1);
        return customerDTO1;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Saving new customer: {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = repository.save(customer);
        return customerMapper.fromEntity(customer);
    }

    @Override
    public List<CustomerDTO> findAllcustomer() {
        log.debug("Finding all customers");
        return customerRepository.findAll().stream().map(customer -> {
                 return customerMapper.fromEntity(customer);
                }).toList();
    }

    @Override
    public CustomerDTO updateCustomer(FileCustomerDTO customerDTO)  {
        log.debug("Updating customer: {}", customerDTO);
        return findOneCustomer(customerDTO.getId()).map(existingCustumer ->{
            if (customerDTO.getBalance() != null){
                existingCustumer.setBalance(customerDTO.getBalance());
            }
            if (customerDTO.getBiographie() != null){
                existingCustumer.setBiographie(customerDTO.getBiographie());
            }
            if (customerDTO.getEmail() != null){
                existingCustumer.setEmail(customerDTO.getEmail());
            }
            if (customerDTO.getFirstName() != null){
                existingCustumer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null){
                existingCustumer.setLastName(customerDTO.getLastName());
            }
            if (customerDTO.getCity() != null){
                existingCustumer.setCity(customerDTO.getCity());
            }
            if (customerDTO.getStreet() != null){
                existingCustumer.setStreet(customerDTO.getStreet());
            }

            if (customerDTO.getUrlProfil() != null){
                existingCustumer.setUrlProfil(customerDTO.getUrlProfil());
            }

            if (customerDTO.getSlug() != null){
                existingCustumer.setSlug(customerDTO.getSlug());
            }

            if (customerDTO.getTel() != null){
                existingCustumer.setTel(customerDTO.getTel());
            }

            if (customerDTO.getFileurlImage() != null && !customerDTO.getFileurlImage().isEmpty()) {
                String urlImage = null;
                try {
                    urlImage = filtreStorageService.upload(customerDTO.getFileurlImage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                customerDTO.setUrlProfil(urlImage);
            }

            return save(existingCustumer);
        }).orElse(null);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, FileCustomerDTO customerDTO) throws IOException {
        log.debug("Updating customer: {}", id, customerDTO);
        customerDTO.setId(id);
        return updateCustomer(customerDTO);
    }

    @Override
    public Optional<CustomerDTO> findOneCustomer(Long id) {
        log.debug("Requesting customer: {}", id);
        return customerRepository.findById(id).map(customer ->
                customerMapper.fromEntity(customer));
    }

    @Override
    public Optional<CustomerDTO> findByUserId(Long id) {
        return customerRepository.findCustomerByUserId(id).map(customer ->
                customerMapper.fromEntity(customer));
    }

    @Override
    public Optional<CustomerDTO> getCustomerBySlug(String slug) {
        return customerRepository.findBySlug(slug).map(customer ->
                customerMapper.fromEntity(customer));

    }

    @Override
    public CustomerDTO uploadCustumerImage(Long id, FileCustomerDTO fileCustomerDTO) throws IOException {
        Optional<CustomerDTO> optionalcustumer = findOneCustomer(id);
        CustomerDTO customerDTO = optionalcustumer.orElseThrow(() ->
                new IllegalArgumentException("Custumer not found with id: " + id)
        );

        if (fileCustomerDTO.getFirstName() != null) {
            customerDTO.setCity(fileCustomerDTO.getCity());
        }

        if (fileCustomerDTO.getFileurlImage() != null && !fileCustomerDTO.getFileurlImage().isEmpty()) {
            String urlImage = filtreStorageService.upload(fileCustomerDTO.getFileurlImage());
            customerDTO.setUrlProfil(urlImage);
        }

        if (fileCustomerDTO.getLastName() != null) {
            customerDTO.setLastName(fileCustomerDTO.getLastName());
        }

        if (fileCustomerDTO.getBalance() != null) {
            customerDTO.setBalance(fileCustomerDTO.getBalance());
        }
        if (fileCustomerDTO.getBiographie() != null) {
            customerDTO.setBiographie(fileCustomerDTO.getBiographie());
        }
        if (fileCustomerDTO.getEmail() != null) {
            customerDTO.setEmail(fileCustomerDTO.getEmail());
        }
        if (fileCustomerDTO.getStreet() != null) {
            customerDTO.setStreet(fileCustomerDTO.getStreet());
        }
        return save(customerDTO);
    }
}
