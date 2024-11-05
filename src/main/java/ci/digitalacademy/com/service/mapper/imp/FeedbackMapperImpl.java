package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Feedback;
import ci.digitalacademy.com.service.dto.FeedbackDTO;
import ci.digitalacademy.com.service.mapper.FeedbackMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackMapperImpl implements FeedbackMapper {

    private final ModelMapper modelMapper;

    @Override
    public FeedbackDTO fromEntity(Feedback entity) {
        return modelMapper.map(entity, FeedbackDTO.class);
    }

    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        return modelMapper.map(dto, Feedback.class);
    }
}
