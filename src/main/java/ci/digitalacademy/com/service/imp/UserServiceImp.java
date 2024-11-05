package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.repository.UserRepository;
import ci.digitalacademy.com.service.UserService;
import ci.digitalacademy.com.service.dto.UserDTO;
import ci.digitalacademy.com.service.mapper.UserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    final private UserRepository userRepository;
    final private UserMapper userMapper;
    @Override
    public UserDTO save(UserDTO userDTO) {
        return userMapper.fromEntity(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return getById(userDTO.getId()).map(userDTO1 -> {
            if (userDTO.getUserName() != null) {
                userDTO1.setUserName(userDTO.getUserName());
            }
            return save(userDTO1);
        }).orElse(null);
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        userDTO.setId(id);
        return update(userDTO);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> {
            return userMapper.fromEntity(user);
        }).toList();
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        return userRepository.findById(id).map(user -> {
            return userMapper.fromEntity(user);
        });
    }

    @Override
    public Optional<UserDTO> getBySlug(String slug) {
        return userRepository.findBySlug(slug).map(user -> {
            return userMapper.fromEntity(user);
        });
    }

    @Override
    public UserDTO getByUserName(String userName) {
        return userRepository.findByUserName(userName).map(user -> {
            return userMapper.fromEntity(user);
        }).orElse(null);
    }
}
