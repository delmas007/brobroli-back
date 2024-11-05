package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RoleDTO {
    private String role;
    @JsonIgnore
    private Set<UserDTO> user;
}
