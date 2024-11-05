package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Provider;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.mapper.ProviderMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProviderMapperImp implements ProviderMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProviderDTO fromEntity(Provider entity) {
        return modelMapper.map(entity, ProviderDTO.class);
    }

    @Override
    public Provider toEntity(ProviderDTO dto) {
        return modelMapper.map(dto, Provider.class);

    }
}
