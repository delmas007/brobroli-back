package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.User;
import ci.digitalacademy.com.service.dto.UserDTO;
import ci.digitalacademy.com.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImp implements UserMapper {
    private final ModelMapper modelMapper;
    @Override
    public UserDTO fromEntity(User entity) {
        return modelMapper.map(entity,UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto,User.class);
    }
}
