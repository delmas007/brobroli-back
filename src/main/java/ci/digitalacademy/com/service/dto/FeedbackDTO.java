package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.Collaboration;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FeedbackDTO {

    private Long id;
    private String commentaire;
    private int rating;
    private LocalDate createAt;
    private LocalDate updateAt;
    private String slug;

    private CollaborationDTO collaboration;
}
