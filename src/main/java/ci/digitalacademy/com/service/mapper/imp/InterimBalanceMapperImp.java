package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.InterimBalance;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.InterimBalanceDTO;
import ci.digitalacademy.com.service.mapper.InterimBalanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class InterimBalanceMapperImp implements InterimBalanceMapper {
    private final ModelMapper modelMapper;

    @Override
    public InterimBalanceDTO fromEntity(InterimBalance entity) {
        return modelMapper.map(entity, InterimBalanceDTO.class);
    }

    @Override
    public InterimBalance toEntity(InterimBalanceDTO dto) {
        return modelMapper.map(dto, InterimBalance.class);
    }
}
