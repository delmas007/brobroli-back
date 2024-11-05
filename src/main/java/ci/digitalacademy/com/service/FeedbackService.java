package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.FeedbackDTO;
import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    FeedbackDTO saveFeedback(FeedbackDTO  feedbackDTO);

    FeedbackDTO save(FeedbackDTO  feedbackDTO);

    FeedbackDTO update( FeedbackDTO  feedbackDTO);

    List< FeedbackDTO> findAll();

    Optional< FeedbackDTO> findOneById(Long id);

    FeedbackDTO update(FeedbackDTO  feedbackDTO, Long id);

    void deleteById(Long id);

}
