package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceDTO {
    private Long id;
    private String slug;
    private Float sum;
}
