package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Balance;
import ci.digitalacademy.com.service.dto.BalanceDTO;
import ci.digitalacademy.com.service.mapper.BalanceMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BalanceMapperImp implements BalanceMapper {
    private final ModelMapper modelMapper;
    @Override
    public BalanceDTO fromEntity(Balance entity) {
        return modelMapper.map(entity,BalanceDTO.class);
    }

    @Override
    public Balance toEntity(BalanceDTO dto) {
        return modelMapper.map(dto,Balance.class);
    }
}
