package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skills, Long> {
    List<Skills> findAllByProviderId(Long id);
}
