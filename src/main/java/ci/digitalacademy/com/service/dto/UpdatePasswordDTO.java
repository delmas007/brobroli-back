package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
    private String code;
    private String email;
    private String password;
}
