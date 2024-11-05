package ci.digitalacademy.com.model;

import ci.digitalacademy.com.model.enume.TypeService;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeService typeService;
    private String description;
    private Float price;
    private Float duration;
    private String slug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToOne(mappedBy = "service")
    private Collaboration collaboration;
}
