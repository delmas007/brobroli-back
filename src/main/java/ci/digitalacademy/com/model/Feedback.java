package ci.digitalacademy.com.model;

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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentaire;
    private int rating;
    private LocalDate createAt;
    private LocalDate updateAt;
    private String slug;

    @ManyToOne
    private Collaboration collaboration;
}
