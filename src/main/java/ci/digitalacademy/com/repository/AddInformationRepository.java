package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.AddInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddInformationRepository extends JpaRepository<AddInformation, Long> {
    Optional<AddInformation> findBySlug(String slug);
}
