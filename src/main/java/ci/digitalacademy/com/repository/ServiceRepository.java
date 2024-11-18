package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Service;
import ci.digitalacademy.com.model.enume.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findByTypeServiceAndPriceBetween(TypeService typeService, Float minPrice, Float maxPrice);

    List<Service> findByTypeService(TypeService typeService);

    Optional<Service> findBySlug(String slug);
}
