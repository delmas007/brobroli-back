package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.ValidationService;
import ci.digitalacademy.com.service.dto.UpdatePasswordDTO;
import ci.digitalacademy.com.service.dto.ValidationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
public class ValidationRessource {
    private final ValidationService validationService;

    @PostMapping("/active-account/{code}")
    public int activeAccount(@PathVariable String code){
       return validationService.activeAccount(code);
    }

    @PostMapping("/resend-mail/{email}")
    public int resendMail(@PathVariable String email){
       return validationService.resendMail(email);
    }

    @PostMapping("/send-code-reset-password/{email}")
    public int sendCodeResetPasswordByEmail(@PathVariable String email){
        return validationService.sendCodeByEmail(email);
    }

    @PostMapping("/nouveau-mot-de-passe")
    public int nouveauMotDePasse(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        return validationService.newMotDePasse(updatePasswordDTO);
    }

}
