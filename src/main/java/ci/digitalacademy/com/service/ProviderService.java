package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.FileProviderDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProviderService {

    ProviderDTO save( ProviderDTO  providerDTO);
    ProviderDTO saveProvider(FileProviderDTO  fileProviderDTO) throws IOException;

    ProviderDTO update( FileProviderDTO  providerDTO)throws IOException;

    ProviderDTO uploadProviderImage(Long id, FileProviderDTO fileProviderDTO) throws IOException;

    Optional< ProviderDTO> findOneById(Long id);
    Optional< ProviderDTO> findByUserId(Long id);

    Optional< ProviderDTO> findOneBySlug(String slug);

    List< ProviderDTO> findAll();

    ProviderDTO update( FileProviderDTO  providerDTO, Long id) throws IOException;

    void activateProvider(Long id);
    void deactivateProvider(Long id);



}
