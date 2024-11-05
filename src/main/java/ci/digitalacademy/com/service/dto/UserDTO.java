package ci.digitalacademy.com.service.dto;
import ci.digitalacademy.com.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String slug;
    private String userName;
    private String password;
    private RoleDTO role;
    private Boolean rememberMe;
}
