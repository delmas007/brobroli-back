package ci.digitalacademy.com.model;

import ci.digitalacademy.com.model.enume.CollaborationStatus;
import ci.digitalacademy.com.model.enume.CustomerStatusService;
import ci.digitalacademy.com.model.enume.ProviderStatusService;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Collaboration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    @Enumerated(value= EnumType.STRING)
    private CollaborationStatus status;
    private LocalDate createAt;
    private LocalDate updateAt;
    @Enumerated(value= EnumType.STRING)
    private CustomerStatusService customerStatusService;
    @Enumerated(value= EnumType.STRING)
    private ProviderStatusService providerStatusService;

    @OneToOne
    private Service service;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    private InterimBalance interimBalance;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Feedback> feedback;
}
