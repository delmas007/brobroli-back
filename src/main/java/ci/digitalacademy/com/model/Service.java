package ci.digitalacademy.com.model;

import ci.digitalacademy.com.model.enume.ServiceStatus;
import ci.digitalacademy.com.model.enume.TypeService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    private ServiceStatus status = ServiceStatus.ON_HOLD;
    private String description;
    private Float price;
    private Float duration;
    private String slug;
    private LocalDate createAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToOne(mappedBy = "service")
    private Collaboration collaboration;
}
