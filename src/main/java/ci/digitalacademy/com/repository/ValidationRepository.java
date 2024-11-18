package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.model.Provider;
import ci.digitalacademy.com.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation,Long> {
    @Query("SELECT v FROM Validation v WHERE v.code = :code")
    Optional<Validation> findByCode(@Param("code") String code);

    Optional<Validation> findByProvider(Provider provider);

    Optional<Validation> findByCustomer(Customer customer);

    void deleteAllByExpirationBefore(Instant now);

    void deleteById(int id);
}
