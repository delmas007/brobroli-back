package ci.digitalacademy.com.model;

import ci.digitalacademy.com.model.enume.MobileMoney;
import ci.digitalacademy.com.model.enume.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String slug;
    private String rising;
    @Enumerated(value= EnumType.STRING)
    private MobileMoney mobileMoney;
    @Enumerated(value= EnumType.STRING)
    private TransactionType transactionType;

}
