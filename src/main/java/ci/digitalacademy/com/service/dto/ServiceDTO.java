package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.Views;
import ci.digitalacademy.com.model.Collaboration;
import ci.digitalacademy.com.model.Provider;
import ci.digitalacademy.com.model.enume.TypeService;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private TypeService typeService;
    private String description;
    private Float price;
    private Float duration;
    private String slug;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private ProviderDTO provider;
//    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private CollaborationDTO collaboration;
}
