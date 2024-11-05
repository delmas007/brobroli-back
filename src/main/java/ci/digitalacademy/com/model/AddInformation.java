package ci.digitalacademy.com.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class AddInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String urlImage;
    private String urlCertificat;
    private String description;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
