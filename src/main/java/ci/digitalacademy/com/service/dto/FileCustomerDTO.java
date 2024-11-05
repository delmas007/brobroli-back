package ci.digitalacademy.com.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileCustomerDTO extends CustomerDTO{
    private MultipartFile fileurlImage;
}
