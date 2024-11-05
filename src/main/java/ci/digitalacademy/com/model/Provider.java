package ci.digitalacademy.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Provider extends Person {
    @OneToMany(mappedBy = "provider")
    private Set<Skills> skills;

    @OneToMany(mappedBy = "provider")
    private Set<AddInformation> addInformation;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
    private Set<Service> service;
}