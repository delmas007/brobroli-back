package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.InterimBalanceDTO;

import java.util.List;
import java.util.Optional;

public interface InterimBalanceService {

    InterimBalanceDTO save(InterimBalanceDTO interimBalanceDTO);

    InterimBalanceDTO saveInterimBalance(InterimBalanceDTO interimBalanceDTO);

    List<InterimBalanceDTO> findAll();


    InterimBalanceDTO update(Long id, InterimBalanceDTO interimBalanceDTO);

    InterimBalanceDTO update(InterimBalanceDTO interimBalanceDTO);

    Optional<InterimBalanceDTO> getById(Long id);

    Optional<InterimBalanceDTO> getBySlug(String username);


}
