package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.AddInformation;
import ci.digitalacademy.com.repository.AddInformationRepository;
import ci.digitalacademy.com.service.AddInformationService;
import ci.digitalacademy.com.service.FiltreStorageService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.dto.AddInformationDTO;
import ci.digitalacademy.com.service.dto.FileAddInformationDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.mapper.AddInformationMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AddInformationserviceImpl implements AddInformationService {

    private final AddInformationMapper addInformationMapper;
    private final AddInformationRepository addInformationRepository;
    private final FiltreStorageService filtreStorageService;
    private final ProviderService providerService;

    @Override
    public AddInformationDTO save(AddInformationDTO addInformationDTO) {
        log.info("Saving AddInformation: {}", addInformationDTO);
        AddInformation addInformation = addInformationMapper.toEntity(addInformationDTO);
        AddInformation savesAddInformation = addInformationRepository.save(addInformation);
        return addInformationMapper.fromEntity(savesAddInformation);
    }

    @Override
    public AddInformationDTO saveAddInformation(FileAddInformationDTO fileAddInformationDTO) throws IOException {
        log.info("Saving AddInformation: {}", fileAddInformationDTO);
        final String slug = SlugifyUtils.generate(fileAddInformationDTO.getDescription());
        fileAddInformationDTO.setSlug(slug);
        Optional<ProviderDTO> oneById = providerService.findOneById(fileAddInformationDTO.getProvider().getId());

        if (fileAddInformationDTO.getFileurlImage() != null && !fileAddInformationDTO.getFileurlImage().isEmpty()) {
            String imageUrl = filtreStorageService.upload(fileAddInformationDTO.getFileurlImage());
            fileAddInformationDTO.setUrlImage(imageUrl);
        }
        fileAddInformationDTO.setProvider(oneById.get());
        AddInformation addInformation = addInformationMapper.toEntity(fileAddInformationDTO);
        AddInformation savedAddInformation = addInformationRepository.save(addInformation);
        return addInformationMapper.fromEntity(savedAddInformation);
    }

    @Override
    public Optional<AddInformationDTO> findOneById(Long id) {
        return addInformationRepository.findById(id).map(addInformation -> {
            return addInformationMapper.fromEntity(addInformation);
        });
    }

    @Override
    public Optional<AddInformationDTO> findOneBySlug(String slug) {
        return addInformationRepository.findBySlug(slug).map(addInformation -> {
            return addInformationMapper.fromEntity(addInformation);
        });
    }

    @Override
    public List<AddInformationDTO> findAll() {
        return addInformationRepository.findAll().stream().map(addInformation -> {
            return addInformationMapper.fromEntity(addInformation);
        }).toList();
    }


    @Override
    public AddInformationDTO uploadAddInformationPicture(Long id, FileAddInformationDTO fileAddInformationDTO) throws IOException {
        Optional<AddInformationDTO> optionalAddInformation = findOneById(id);
        AddInformationDTO addInformation = optionalAddInformation.orElseThrow(() ->
                new IllegalArgumentException("AddInformation not found with id: " + id)
        );

        if (fileAddInformationDTO.getDescription() != null) {
            addInformation.setDescription(fileAddInformationDTO.getDescription());
        }

        if (fileAddInformationDTO.getFileurlImage() != null && !fileAddInformationDTO.getFileurlImage().isEmpty()) {
            String urlImage = filtreStorageService.upload(fileAddInformationDTO.getFileurlImage());
            addInformation.setUrlImage(urlImage);
        }

        if (fileAddInformationDTO.getProvider() != null) {
            addInformation.setProvider(fileAddInformationDTO.getProvider());
        }

        if (fileAddInformationDTO.getUrlCertificat() != null) {
            addInformation.setUrlCertificat(fileAddInformationDTO.getUrlCertificat());
        }
        return save(addInformation);
    }

    @Override
    public void delecte(Long id) {
        addInformationRepository.deleteById(id);
    }
}
