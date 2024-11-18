package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Role;
import ci.digitalacademy.com.service.dto.RoleDTO;
import ci.digitalacademy.com.service.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleMapperImp implements RoleMapper {
    private final ModelMapper modelMapper;
    @Override
    public RoleDTO fromEntity(Role entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }
}
