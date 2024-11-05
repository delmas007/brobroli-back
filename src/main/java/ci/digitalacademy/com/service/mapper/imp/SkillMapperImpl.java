package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Skills;
import ci.digitalacademy.com.service.dto.SkillDTO;
import ci.digitalacademy.com.service.mapper.SkillMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillMapperImpl implements SkillMapper {

    private final ModelMapper modelMapper;

    @Override
    public SkillDTO fromEntity(Skills entity) {
        return modelMapper.map(entity, SkillDTO.class);
    }

    @Override
    public Skills toEntity(SkillDTO dto) {
        return modelMapper.map(dto, Skills.class);
    }
}
