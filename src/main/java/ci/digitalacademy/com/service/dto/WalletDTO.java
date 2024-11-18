package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.enume.MobileMoney;
import ci.digitalacademy.com.model.enume.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDTO {

    private Long id;
    private String slug;
    private String rising;
    private MobileMoney mobileMoney;
    private TransactionType transactionType;
}
