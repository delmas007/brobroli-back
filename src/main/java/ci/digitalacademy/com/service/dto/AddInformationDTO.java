package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddInformationDTO {

    private Long id;
    private String slug;
    private String urlImage;
    private String urlCertificat;
    private String description;

    private ProviderDTO provider;
}
