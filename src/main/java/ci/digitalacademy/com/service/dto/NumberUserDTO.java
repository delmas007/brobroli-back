package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NumberUserDTO {

    private Integer numberUser;
    private Integer numberProvider;
    private Integer numberCustomer;
    private List<ProviderDTO> listProvider;
    private List<CustomerDTO> listCustomer;
}
