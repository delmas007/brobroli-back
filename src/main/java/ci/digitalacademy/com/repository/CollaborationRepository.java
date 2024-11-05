package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Collaboration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborationRepository extends JpaRepository<Collaboration,Long> {
    @Query("SELECT c FROM Collaboration c WHERE c.service.provider.id = :providerId")
    List<Collaboration> findAllByProviderId(@Param("providerId") Long providerId);

    @Query("SELECT c FROM Collaboration c WHERE c.customer.id = :customerId")
    List<Collaboration> findAllByCustomerId(@Param("customerId") Long customerId);
}
