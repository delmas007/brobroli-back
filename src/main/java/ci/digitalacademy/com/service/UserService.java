package ci.digitalacademy.com.service;

import ci.digitalacademy.com.model.User;
import ci.digitalacademy.com.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO update(UserDTO userDTO,Long id);
    List<UserDTO> findAll();
    Optional<UserDTO> getById(Long id);
    Optional<UserDTO> getBySlug(String slug);
    UserDTO getByUserName(String userName);
}
