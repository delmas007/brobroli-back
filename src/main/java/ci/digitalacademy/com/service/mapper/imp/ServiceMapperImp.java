package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Service;
import ci.digitalacademy.com.service.dto.ServiceDTO;
import ci.digitalacademy.com.service.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapperImp implements ServiceMapper {
    private final ModelMapper modelMapper;
    @Override
    public ServiceDTO fromEntity(Service entity) {
        return modelMapper.map(entity,ServiceDTO.class);
    }

    @Override
    public Service toEntity(ServiceDTO dto) {
        return modelMapper.map(dto,Service.class);
    }
}
