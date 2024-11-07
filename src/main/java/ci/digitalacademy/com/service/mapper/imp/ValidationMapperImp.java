package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Validation;
import ci.digitalacademy.com.service.dto.ValidationDTO;
import ci.digitalacademy.com.service.mapper.ValidationMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationMapperImp implements ValidationMapper {
    private final ModelMapper modelMapper;
    @Override
    public ValidationDTO fromEntity(Validation entity) {
        return modelMapper.map(entity,ValidationDTO.class);
    }

    @Override
    public Validation toEntity(ValidationDTO dto) {
        return modelMapper.map(dto,Validation.class);
    }
}
