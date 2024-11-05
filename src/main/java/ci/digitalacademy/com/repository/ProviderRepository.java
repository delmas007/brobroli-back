package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findBySlug(String slug);
    Optional<Provider> findProviderByUserId(Long userId);
}
