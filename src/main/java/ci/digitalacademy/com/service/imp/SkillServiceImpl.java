package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Skills;
import ci.digitalacademy.com.repository.SkillRepository;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.SkillService;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.dto.SkillDTO;
import ci.digitalacademy.com.service.mapper.SkillMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final ProviderService providerService;

    @Override
    public SkillDTO save(SkillDTO skillDTO) {
        Skills skills = skillMapper.toEntity(skillDTO);
        log.info("Saving Skill: {}", skills);
        Skills saveskills = skillRepository.save(skills);
        return skillMapper.fromEntity(saveskills);
    }

    @Override
    public SkillDTO saveskill(SkillDTO skillDTO,Long id) {
        Optional<ProviderDTO> providerOp = providerService.findOneById(id);
        skillDTO.setProvider(providerOp.get());
        final String slug = SlugifyUtils.generate(skillDTO.getSkillName());
        skillDTO.setSlug(slug);
        return save(skillDTO);
    }

    @Override
    public Optional<SkillDTO> findOneById(Long id) {
        return skillRepository.findById(id).map(skills -> {
            return skillMapper.fromEntity(skills);
        });
    }

    @Override
    public SkillDTO partialupdate(SkillDTO skillDTO) {
        return findOneById(skillDTO.getId()).map(existingSkill->{
            if (skillDTO.getSkillName() != null) {
                existingSkill.setSkillName(skillDTO.getSkillName());
            }
            if (skillDTO.getSkillLevel() != null) {
                existingSkill.setSkillLevel(skillDTO.getSkillLevel());
            }
            return save(existingSkill);
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public List<SkillDTO> findAllByProviderId(Long id) {
        return skillRepository.findAllByProviderId(id).stream().map(skillMapper::fromEntity).toList();
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public SkillDTO update(SkillDTO skillDTO, Long id) {
        skillDTO.setId(id);
        return partialupdate(skillDTO);
    }
}
