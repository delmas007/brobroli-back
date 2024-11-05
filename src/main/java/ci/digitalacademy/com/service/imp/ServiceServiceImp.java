package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.enume.TypeService;
import ci.digitalacademy.com.repository.ServiceRepository;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.ServiceService;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.ServiceDTO;
import ci.digitalacademy.com.service.mapper.ServiceMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
@Slf4j
public class ServiceServiceImp implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final ProviderService providerService;

    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        log.debug("Saving new service: {}", serviceDTO);
        return serviceMapper.fromEntity(serviceRepository.save(serviceMapper.toEntity(serviceDTO)));
    }

    public ServiceDTO saveService(ServiceDTO serviceDTO, Long id) {
        log.debug("Saving new service: {}", serviceDTO);
        Optional<ProviderDTO> provider = providerService.findOneById(id);
        if (provider.isPresent()){
            serviceDTO.setProvider(provider.get());
            return serviceMapper.fromEntity(serviceRepository.save(serviceMapper.toEntity(serviceDTO)));
        }
        return null;
    }

    @Override
    public ServiceDTO update(ServiceDTO serviceDTO) {
        return findOneById(serviceDTO.getId()).map(serviceDTO1 -> {
            if (serviceDTO.getTypeService() != null){
                serviceDTO1.setTypeService(serviceDTO.getTypeService());
            }
            if (serviceDTO.getPrice() != null){
                serviceDTO1.setPrice(serviceDTO.getPrice());
            }
            if (serviceDTO.getDescription() != null){
                serviceDTO1.setDescription(serviceDTO.getDescription());
            }
            if (serviceDTO.getDuration() != null){
                serviceDTO1.setDuration(serviceDTO.getDuration());
            }
            return save(serviceDTO1);
        }).orElse(null);
    }

    @Override
    public Optional<ServiceDTO> findOneById(Long id) {
        return serviceRepository.findById(id).map(service -> {
            return serviceMapper.fromEntity(service);
        });
    }

    @Override
    public Optional<ServiceDTO> findOneBySlug(String slug) {
        return serviceRepository.findBySlug(slug).map(service -> {
            return serviceMapper.fromEntity(service);
        });
    }

    @Override
    public List<ServiceDTO> findAll() {
        return serviceRepository.findAll().stream().map(service -> {
            return serviceMapper.fromEntity(service);
        }).toList();
    }

    @Override
    public ServiceDTO update(ServiceDTO serviceDTO, Long id) {
        serviceDTO.setId(id);
        return update(serviceDTO);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceDTO> searchServicesByTypeAndPriceRange(String typeService, Float minPrice, Float maxPrice) {
        TypeService typeService1 = TypeService.valueOf(typeService);
        return serviceRepository.findByTypeServiceAndPriceBetween(typeService1, minPrice, maxPrice).stream().map(service -> {
            return serviceMapper.fromEntity(service);
        }).toList();
    }

    @Override
    public List<ServiceDTO> searchServicesByType(String typeService) {
        TypeService typeService1 = TypeService.valueOf(typeService);
        return serviceRepository.findByTypeService(typeService1).stream().map(service -> {
            return serviceMapper.fromEntity(service);
        }).toList();
    }
}
