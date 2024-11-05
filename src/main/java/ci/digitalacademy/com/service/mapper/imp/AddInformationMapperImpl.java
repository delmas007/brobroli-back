package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.AddInformation;
import ci.digitalacademy.com.service.dto.AddInformationDTO;
import ci.digitalacademy.com.service.mapper.AddInformationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddInformationMapperImpl implements AddInformationMapper {

    private final ModelMapper modelMapper;

    @Override
    public AddInformationDTO fromEntity(AddInformation entity) {
        return modelMapper.map(entity, AddInformationDTO.class);
    }

    @Override
    public AddInformation toEntity(AddInformationDTO dto) {
        return modelMapper.map(dto, AddInformation.class);
    }
}
