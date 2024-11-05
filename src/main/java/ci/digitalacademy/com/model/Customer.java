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
public class Customer extends Person {
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer")
    private Set<Collaboration> collaboration;
}
