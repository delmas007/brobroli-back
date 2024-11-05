package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.FeedbackService;
import ci.digitalacademy.com.service.dto.FeedbackDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@Slf4j
@RequiredArgsConstructor
public class FeedbackResource {

private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> save(@RequestBody FeedbackDTO feedback){
        log.debug("REST request to save feedback: {}", feedback);
        return new ResponseEntity<>(feedbackService.save(feedback), HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public FeedbackDTO update(@RequestBody FeedbackDTO feedback, @PathVariable Long id){
        log.debug("REST request to update: {}", feedback);
        return feedbackService.update(feedback, id);
    }

    @GetMapping
    public List<FeedbackDTO> findAll(){
        log.debug("REST request to find all");
        return feedbackService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Long id){
        log.debug("REST request to find one by id: {}", id);
        return new ResponseEntity<>(feedbackService.findOneById(id),HttpStatus.OK );
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id){
        log.debug("");
       feedbackService.deleteById(id);
    }
}
