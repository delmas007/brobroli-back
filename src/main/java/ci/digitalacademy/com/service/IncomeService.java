package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.IncomeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IncomeService {
    IncomeDTO save(IncomeDTO incomeDTO);
    List<IncomeDTO> findAll();
}
