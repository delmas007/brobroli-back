package ci.digitalacademy.com.service;

import ci.digitalacademy.com.model.enume.TypeService;
import ci.digitalacademy.com.service.dto.ServiceDTO;
import ci.digitalacademy.com.service.dto.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    ServiceDTO save(ServiceDTO serviceDTO);
    ServiceDTO saveService(ServiceDTO serviceDTO,Long id);

    ServiceDTO update( ServiceDTO  serviceDTO);

    Optional< ServiceDTO> findOneById(Long id);

    Optional< ServiceDTO> findOneBySlug(String slug);

    List< ServiceDTO> findAll();

    ServiceDTO update( ServiceDTO  serviceDTO, Long id);

    void deleteById(Long id);

    List<ServiceDTO> searchServicesByTypeAndPriceRange(String typeService, Float minPrice, Float maxPrice);
    List<ServiceDTO> searchServicesByType(String typeService);
}
