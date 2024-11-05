package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerMapperImp implements CustomerMapper {


    private final ModelMapper modelMapper;


    @Override
    public CustomerDTO fromEntity(Customer entity) {
        return modelMapper.map(entity, CustomerDTO.class);
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }
}
