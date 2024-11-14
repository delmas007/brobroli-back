package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.NumberUserDTO;

public interface AdminService {
    NumberUserDTO numberListUser();
    void valid(Long id_service);
    void reject(Long id_service);

}
