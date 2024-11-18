package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Service;
import ci.digitalacademy.com.model.enume.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findByServiceTypeAndPriceBetween(ServiceType serviceType, Float minPrice, Float maxPrice);

    List<Service> findByServiceType(ServiceType serviceType);

    Optional<Service> findBySlug(String slug);
}
