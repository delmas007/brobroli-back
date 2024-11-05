package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findBySlug(String slug);
    Optional<Customer> findCustomerByUserId(Long userId);
}
