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
@Table
public class Role {
    @Id
    private String role;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> user;
}
