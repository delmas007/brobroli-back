package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Feedback;
import ci.digitalacademy.com.repository.FeedbackRepository;
import ci.digitalacademy.com.service.FeedbackService;
import ci.digitalacademy.com.service.dto.FeedbackDTO;
import ci.digitalacademy.com.service.mapper.FeedbackMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

private final FeedbackRepository feedbackRepository;
private final FeedbackMapper feedbackMapper;


    @Override
    public FeedbackDTO saveFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
        return feedbackMapper.fromEntity(feedbackRepository.save(feedback));
    }

    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        feedbackDTO.setCreateAt(LocalDate.now());
        feedbackDTO.setSlug(SlugifyUtils.generate("azert"));
        Feedback feedback = feedbackMapper.toEntity( feedbackDTO);
        return feedbackMapper.fromEntity(feedbackRepository.save(feedback));

    }

    @Override
    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        return findOneById(feedbackDTO.getId()).map(existingFeedback ->{
            existingFeedback.setUpdateAt(LocalDate.now());
            if (feedbackDTO.getCommentaire() != null){
                existingFeedback.setCommentaire(feedbackDTO.getCommentaire());
            }
            if (feedbackDTO.getRating() != 0){
                existingFeedback.setRating(feedbackDTO.getRating());
            }

            if (feedbackDTO.getSlug() != null){
                existingFeedback.setSlug(feedbackDTO.getSlug());
            }

            return saveFeedback(existingFeedback);
        }).orElse(null);

    }

    @Override
    public List<FeedbackDTO> findAll() {
        return feedbackRepository.findAll().stream().map(feedback -> feedbackMapper.fromEntity(feedback)).toList();
    }

    @Override
    public Optional<FeedbackDTO> findOneById(Long id) {
        return feedbackRepository.findById(id).map(feedback -> {
        return feedbackMapper.fromEntity(feedback);

    });
    }

    @Override
    public FeedbackDTO update(FeedbackDTO feedbackDTO, Long id) {
       feedbackDTO.setId(id);
        return update(feedbackDTO);
    }

    @Override
    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }

}
