package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Income;
import ci.digitalacademy.com.service.dto.IncomeDTO;
import ci.digitalacademy.com.service.mapper.IncomeMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IcomeMapperImp implements IncomeMapper {
    private final ModelMapper modelMapper;
    @Override
    public IncomeDTO fromEntity(Income entity) {
        return modelMapper.map(entity, IncomeDTO.class);
    }

    @Override
    public Income toEntity(IncomeDTO dto) {
        return modelMapper.map(dto, Income.class);
    }
}
