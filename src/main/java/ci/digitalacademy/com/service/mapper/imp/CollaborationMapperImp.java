package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Collaboration;
import ci.digitalacademy.com.service.dto.CollaborationDTO;
import ci.digitalacademy.com.service.mapper.CollaborationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollaborationMapperImp implements CollaborationMapper {
    private final ModelMapper modelMapper;
    @Override
    public CollaborationDTO fromEntity(Collaboration entity) {
        return modelMapper.map(entity, CollaborationDTO.class);
    }

    @Override
    public Collaboration toEntity(CollaborationDTO dto) {
        return modelMapper.map(dto, Collaboration.class);
    }
}
