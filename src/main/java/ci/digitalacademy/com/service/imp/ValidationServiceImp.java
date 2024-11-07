package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.model.Provider;
import ci.digitalacademy.com.model.Validation;
import ci.digitalacademy.com.repository.CustomerRepository;
import ci.digitalacademy.com.repository.ProviderRepository;
import ci.digitalacademy.com.repository.ValidationRepository;
import ci.digitalacademy.com.service.NotificationMailService;
import ci.digitalacademy.com.service.ValidationService;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.UpdatePasswordDTO;
import ci.digitalacademy.com.service.dto.ValidationDTO;
import ci.digitalacademy.com.service.mapper.CustomerMapper;
import ci.digitalacademy.com.service.mapper.ProviderMapper;
import ci.digitalacademy.com.service.mapper.ValidationMapper;
import ci.digitalacademy.com.web.exception.EntityNotFoundException;
import ci.digitalacademy.com.web.exception.ErrorCodes;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationServiceImp implements ValidationService {
    private final ValidationRepository validationRepository;
    private final NotificationMailService notificationMailServiceImp;
    private final ValidationMapper validationMapper;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerCustomer(CustomerDTO customerDTO) {
        ValidationDTO validation = new ValidationDTO();
        deleteByCustomer(customerDTO);
        validation.setCustomer(customerDTO);
        Instant creation = Instant.now();
        Instant expiration = creation.plus((Duration.ofMinutes(10)));
        validation.setCreation(creation);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailCustomer(validationDTO);
    }

    @Override
    public void registerProvider(ProviderDTO providerDTO) {
        ValidationDTO validation = new ValidationDTO();
        deleteByProvider(providerDTO);
        validation.setProvider(providerDTO);
        Instant creation = Instant.now();
        Instant expiration = creation.plus((Duration.ofMinutes(10)));
        validation.setCreation(creation);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailProvider(validationDTO);
    }

    @Override
    public void codeChangePasswordCustomer(CustomerDTO customerDTO) {
        ValidationDTO validation = new ValidationDTO();
        deleteByCustomer(customerDTO);
        validation.setCustomer(customerDTO);
        Instant creation = Instant.now();
        Instant expiration = creation.plus((Duration.ofMinutes(10)));
        validation.setCreation(creation);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailMotCustomer(validationDTO);
    }

    @Override
    public void codeChangePasswordProvider(ProviderDTO providerDTO) {
        ValidationDTO validation = new ValidationDTO();
        deleteByProvider(providerDTO);
        validation.setProvider(providerDTO);
        Instant creation = Instant.now();
        Instant expiration = creation.plus((Duration.ofMinutes(10)));
        validation.setCreation(creation);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailMotProvider(validationDTO);
    }

    @Override
    public int resendMail(String email) {
        Optional<Provider> provider = providerRepository.findByEmail(email);
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (provider.isPresent()){
            resendMailProvider(providerMapper.fromEntity(provider.get()));
            return 1;
        }else if (customer.isPresent()){
            resendMailCustomer(customerMapper.fromEntity(customer.get()));
            return 1;
        }else {
            throw new EntityNotFoundException("l'email n'existe pas", ErrorCodes.UTILISATEUR_DEJA_EXIST);
        }
    }

    @Override
    public void resendMailCustomer(CustomerDTO customerDTO) {
        ValidationDTO validation = validationMapper.fromEntity(validationRepository.findByCustomer(customerMapper.toEntity(customerDTO)).orElseThrow(() -> new EntityNotFoundException("Aucune validation en cours pour cet utilisateur", ErrorCodes.VALIDATION_NOT_FOUND)));
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailCustomer(validationDTO);
    }

    @Override
    public void resendMailProvider(ProviderDTO providerDTO) {
        ValidationDTO validation = validationMapper.fromEntity(validationRepository.findByProvider(providerMapper.toEntity(providerDTO)).orElseThrow(() -> new EntityNotFoundException("Aucune validation en cours pour cet utilisateur", ErrorCodes.VALIDATION_NOT_FOUND)));
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        ValidationDTO validationDTO = validationMapper.fromEntity(validationRepository.save(validationMapper.toEntity(validation)));
        notificationMailServiceImp.sendNotificationMailProvider(validationDTO);
    }

    @Override
    public void deleteByCustomer(CustomerDTO customerDTO) {
        Optional<Validation> validation = validationRepository.findByCustomer(customerMapper.toEntity(customerDTO));
        validation.ifPresent(value -> validationRepository.deleteById(value.getId()));
    }

    @Override
    public void deleteByProvider(ProviderDTO providerDTO) {
        Optional<Validation> validation = validationRepository.findByProvider(providerMapper.toEntity(providerDTO));
        validation.ifPresent(value -> validationRepository.deleteById(value.getId()));
    }

    @Override
    public int sendCodeByEmail(String email) {
        Optional<Provider> provider = providerRepository.findByEmail(email);
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (provider.isPresent()){
            codeChangePasswordProvider(providerMapper.fromEntity(provider.get()));
            return 1;
        }else if(customer.isPresent()){
            codeChangePasswordCustomer(customerMapper.fromEntity(customer.get()));
            return 2;
        }else {
            throw new EntityNotFoundException("l'email n'existe pas", ErrorCodes.UTILISATEUR_DEJA_EXIST);
        }
    }

    @Override
    public ValidationDTO readAccordingToTheCode(String code) {
        return validationMapper.fromEntity(validationRepository.findByCode(code).orElseThrow(() -> new EntityNotFoundException("Le code est invalide",
                ErrorCodes.CODE_INVALIDE)));
    }

    @Override
    public int newMotDePasse(UpdatePasswordDTO updatePasswordDTO) {
        ValidationDTO validationDTO = readAccordingToTheCode(updatePasswordDTO.getCode());
        Optional<Provider> provider = providerRepository.findByEmail(updatePasswordDTO.getEmail());
        Optional<Customer> customer = customerRepository.findByEmail(updatePasswordDTO.getEmail());
        if (provider.isPresent() && validationDTO.getProvider() != null){
            if(validationDTO.getProvider().getId().equals(provider.get().getId())){
                String mdpCrypte = passwordEncoder.encode(updatePasswordDTO.getPassword());
                provider.get().getUser().setPassword(mdpCrypte);
                providerRepository.save(provider.get());
                return 1;
            }
        }else if(customer.isPresent() && validationDTO.getCustomer() != null){
            if(validationDTO.getCustomer().getId().equals(customer.get().getId())){
                String mdpCrypte = passwordEncoder.encode(updatePasswordDTO.getPassword());
                customer.get().getUser().setPassword(mdpCrypte);
                customerRepository.save(customer.get());
                return 1;
            }
        }else {
            throw new EntityNotFoundException("Email ou code invalide", ErrorCodes.UTILISATEUR_DEJA_EXIST);
        }
        return 0;
    }

    @Override
    public int activeAccount(String code) {
        ValidationDTO validationDTO = readAccordingToTheCode(code);
        if (validationDTO.getProvider() != null){
            activeProbider(code);
            return 1;
        }else if (validationDTO.getCustomer() != null ){
            activeCustomer(code);
            return 1;
        }
        return 0;
    }

    @Override
    public int activeCustomer(String code) {
        ValidationDTO leCodeEstInvalide = readAccordingToTheCode(code);
        if (Instant.now().isAfter(leCodeEstInvalide.getExpiration())) {
            throw new EntityNotFoundException("Le code a expiré", ErrorCodes.CODE_EXPIRE);
        }
        Customer customerActiver = customerRepository.findById(leCodeEstInvalide.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException("Utilisateur pas trouver",
                ErrorCodes.UTILISATEUR_PAS_TROUVER));
        customerActiver.getUser().setActif(true);
        customerRepository.save(customerActiver);
        return 1;
    }

    @Override
    public int activeProbider(String code) {
        ValidationDTO leCodeEstInvalide = readAccordingToTheCode(code);
        if (Instant.now().isAfter(leCodeEstInvalide.getExpiration())) {
            throw new EntityNotFoundException("Le code a expiré", ErrorCodes.CODE_EXPIRE);
        }
        Provider providerActiver = providerRepository.findById(leCodeEstInvalide.getProvider().getId()).orElseThrow(() -> new EntityNotFoundException("Utilisateur pas trouver",
                ErrorCodes.UTILISATEUR_PAS_TROUVER));
        providerActiver.getUser().setActif(true);
        providerRepository.save(providerActiver);
        return 1;
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void deleteAllByExpirationBefore() {
        validationRepository.deleteAllByExpirationBefore(Instant.now());
    }
}
