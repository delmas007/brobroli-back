package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.NumberUserDTO;

public interface AdminService {
    NumberUserDTO numberListUser();
    void valid(Long seriveId);
    void reject(Long seriveId);

}
