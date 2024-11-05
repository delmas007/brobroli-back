package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.model.InterimBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterimBalanceRepository extends JpaRepository<InterimBalance, Long> {
    Optional<InterimBalance> findBySlug(String slug);
}
