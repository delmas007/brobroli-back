package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.SkillDTO;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    SkillDTO save(SkillDTO skillDTO);

    SkillDTO saveskill(SkillDTO skillDTO,Long id);

    Optional<SkillDTO> findOneById(Long id);

    SkillDTO update(SkillDTO skillDTO, Long id);

    SkillDTO partialupdate(SkillDTO skillDTO);

    List<SkillDTO>findAllByProviderId(Long id);

    void deleteById(Long id);

}
