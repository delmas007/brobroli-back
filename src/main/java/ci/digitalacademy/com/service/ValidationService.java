package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.UpdatePasswordDTO;
import ci.digitalacademy.com.service.dto.ValidationDTO;

public interface ValidationService {
    void registerCustomer(CustomerDTO customerDTO);
    void registerProvider(ProviderDTO providerDTO);

    void codeChangePasswordCustomer(CustomerDTO customerDTO);
    void codeChangePasswordProvider(ProviderDTO providerDTO);

    int resendMail(String email);
    void resendMailCustomer(CustomerDTO customerDTO);
    void resendMailProvider(ProviderDTO providerDTO);

    void deleteByCustomer(CustomerDTO customerDTO);
    void deleteByProvider(ProviderDTO providerDTO);

    int sendCodeByEmail(String email);

    ValidationDTO readAccordingToTheCode(String code);

    int newMotDePasse(UpdatePasswordDTO updatePasswordDTO);

    int activeAccount(String code);
    int activeCustomer(String code);
    int activeProbider(String code);
}
