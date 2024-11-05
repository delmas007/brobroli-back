package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.Balance;
import ci.digitalacademy.com.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDTO {

    private Long id;

    private String slug;

    private String firstName;

    private String lastName;

    private String urlProfil;

    private String email;

    private String city;

    private String tel;

    private String street;

    private String biographie;

    private LocalDate createAt;

    private LocalDate updateAt;

    private UserDTO user;

    private BalanceDTO balance;
}
