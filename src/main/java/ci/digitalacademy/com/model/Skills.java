package ci.digitalacademy.com.model;

import ci.digitalacademy.com.model.enume.SkillLevel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillName;
    @Enumerated(value= EnumType.STRING)
    private SkillLevel skillLevel;
    private String slug;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
