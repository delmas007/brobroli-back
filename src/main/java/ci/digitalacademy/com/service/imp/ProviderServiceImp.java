package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.model.Provider;
import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.repository.ProviderRepository;
import ci.digitalacademy.com.repository.RoleRepository;
import ci.digitalacademy.com.security.AuthorityConstants;
import ci.digitalacademy.com.service.FiltreStorageService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.ValidationService;
import ci.digitalacademy.com.service.dto.*;
import ci.digitalacademy.com.service.mapper.ProviderMapper;
import ci.digitalacademy.com.service.mapper.RoleMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import ci.digitalacademy.com.web.exception.EntityNotFoundException;
import ci.digitalacademy.com.web.exception.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProviderServiceImp implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final FiltreStorageService filtreStorageService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidationService validationService;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public ProviderDTO saveProvider(ProviderDTO fileProviderDTO) throws IOException {
        Provider existingUser = providerRepository.findByUserUserName(fileProviderDTO.getUser().getUserName()).orElse(null);
        if (existingUser == null) {
            RoleDTO role2;
            BalanceDTO balanceDTO = new BalanceDTO();
            balanceDTO.setSum(0f);
            role2= roleRepository.findByRole(AuthorityConstants.PROVIDER).map(roleMapper::fromEntity).orElse(null);
            if (fileProviderDTO.getUser() != null) {
                fileProviderDTO.getUser().setRole(role2);
                System.out.println("fileProviderDTO= " + fileProviderDTO.getUser().getRole());
                fileProviderDTO.getUser().setPassword(bCryptPasswordEncoder.encode(fileProviderDTO.getUser().getPassword()));
            }
            fileProviderDTO.setBalance(balanceDTO);
            fileProviderDTO.setCreateAt(LocalDate.now());
            fileProviderDTO.setSlug(SlugifyUtils.generate(fileProviderDTO.getLastName()));
            fileProviderDTO.getUser().setActif(false);
            Provider provider = providerMapper.toEntity(fileProviderDTO);
            ProviderDTO providerDTO = providerMapper.fromEntity(providerRepository.save(provider));
            validationService.registerProvider(providerDTO);
            System.out.println("providerDTO = " + providerDTO.getUser().getRole());
            return providerDTO;
        }else {
            throw new EntityNotFoundException("Utilisateur existe deja", ErrorCodes.UTILISATEUR_DEJA_EXIST);
        }
    }

    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        Provider provider = providerMapper.toEntity( providerDTO);
        return providerMapper.fromEntity(providerRepository.save(provider));

    }

    @Override
    public ProviderDTO update(FileProviderDTO providerDTO)  {
        return findOneById(providerDTO.getId()).map(existingProvider ->{
            existingProvider.setUpdateAt(LocalDate.now());
            ProviderDTO existingProvider1 = extracted(providerDTO, existingProvider);
            return save(existingProvider1);
        }).orElse(null);

    }



    @Override
    public Optional<ProviderDTO> findOneById(Long id) {
        return providerRepository.findById(id).map(provider ->{
            return providerMapper.fromEntity(provider);
        });

    }

    @Override
    public Optional<ProviderDTO> findByUserId(Long id) {
        return providerRepository.findProviderByUserId(id).map(provider ->{
            return providerMapper.fromEntity(provider);
        });
    }

    @Override
    public Optional<ProviderDTO> findOneBySlug(String slug) {
        return providerRepository.findBySlug(slug).map(provider ->{
            return providerMapper.fromEntity(provider);
        });

    }

    @Override
    public List<ProviderDTO> findAll() {
        return providerRepository.findAll().stream().map(provider -> providerMapper.fromEntity(provider)).toList();

    }

    @Override
    public ProviderDTO update(FileProviderDTO providerDTO, Long id) throws IOException {
        providerDTO.setId(id);
        return update(providerDTO);

    }

    private ProviderDTO extracted(FileProviderDTO providerDTO, ProviderDTO existingProvider) {
        if (providerDTO.getBalance() != null){
            existingProvider.setBalance(providerDTO.getBalance());
        }
        if (providerDTO.getBiographie() != null){
            existingProvider.setBiographie(providerDTO.getBiographie());
        }
        if (providerDTO.getEmail() != null){
            existingProvider.setEmail(providerDTO.getEmail());
        }
        if (providerDTO.getFileurlImage() != null && !providerDTO.getFileurlImage().isEmpty()) {
            String urlImage = null;
            try {
                urlImage = filtreStorageService.upload(providerDTO.getFileurlImage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            providerDTO.setUrlProfil(urlImage);
        }
        if (providerDTO.getFirstName() != null){
            existingProvider.setFirstName(providerDTO.getFirstName());
        }
        if (providerDTO.getLastName() != null){
            existingProvider.setLastName(providerDTO.getLastName());
        }
        if (providerDTO.getCity() != null){
            existingProvider.setCity(providerDTO.getCity());
        }
        if (providerDTO.getStreet() != null){
            existingProvider.setStreet(providerDTO.getStreet());
        }

        if (providerDTO.getUrlProfil() != null){
            existingProvider.setUrlProfil(providerDTO.getUrlProfil());
        }

        if (providerDTO.getSlug() != null){
            existingProvider.setSlug(providerDTO.getSlug());
        }

        if (providerDTO.getTel() != null){
            existingProvider.setTel(providerDTO.getTel());
        }
        return existingProvider;
    }

}
