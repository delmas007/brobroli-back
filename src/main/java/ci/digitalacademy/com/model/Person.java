package ci.digitalacademy.com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;
}
