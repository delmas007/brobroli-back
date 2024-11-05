package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.AddInformationDTO;
import ci.digitalacademy.com.service.dto.FileAddInformationDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AddInformationService {

    AddInformationDTO save(AddInformationDTO  addInformationDTO);

    AddInformationDTO saveAddInformation( FileAddInformationDTO fileAddInformationDTO) throws IOException;

    Optional< AddInformationDTO> findOneById(Long id);

    Optional< AddInformationDTO> findOneBySlug(String slug);

    List< AddInformationDTO> findAll();

    AddInformationDTO uploadAddInformationPicture(Long id , FileAddInformationDTO fileAddInformationDTO) throws IOException;

    void delecte(Long id);
}
