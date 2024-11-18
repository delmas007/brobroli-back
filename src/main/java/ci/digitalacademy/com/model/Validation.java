package ci.digitalacademy.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant creation;

    private Instant expiration;

    private String code;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Provider provider;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Customer customer;
}
