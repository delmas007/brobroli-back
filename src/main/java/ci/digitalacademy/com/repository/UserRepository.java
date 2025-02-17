package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findBySlug(String slug);

    Integer countAllByRoleRole(String admin);
}
