package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.enume.ServiceStatus;
import ci.digitalacademy.com.model.enume.TypeService;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private TypeService typeService;
    private ServiceStatus status;
    private String description;
    private Float price;
    private Float duration;
    private String slug;
    private LocalDate createAt;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private ProviderDTO provider;
//    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private CollaborationDTO collaboration;
}
