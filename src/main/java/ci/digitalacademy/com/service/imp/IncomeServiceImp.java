package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.repository.IncomeRepository;
import ci.digitalacademy.com.service.IncomeService;
import ci.digitalacademy.com.service.dto.IncomeDTO;
import ci.digitalacademy.com.service.mapper.IncomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IncomeServiceImp implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    @Override
    public IncomeDTO save(IncomeDTO incomeDTO) {
        return incomeMapper.fromEntity(incomeRepository.save(incomeMapper.toEntity(incomeDTO)));
    }

    @Override
    public List<IncomeDTO> findAll() {
        return incomeRepository.findAll().stream().map(incomeMapper::fromEntity).collect(Collectors.toList());
    }
}
