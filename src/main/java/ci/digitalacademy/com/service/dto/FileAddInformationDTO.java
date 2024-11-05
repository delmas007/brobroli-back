package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileAddInformationDTO extends AddInformationDTO{
    private MultipartFile fileurlImage;
}
