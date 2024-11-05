package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.AddInformationService;
import ci.digitalacademy.com.service.dto.AddInformationDTO;
import ci.digitalacademy.com.service.dto.FileAddInformationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/addInformation")
public class AddInformationRessource {

    private final AddInformationService addInformationService;

    @PostMapping
    public ResponseEntity<AddInformationDTO> saveAddInformation(
            @ModelAttribute FileAddInformationDTO fileAddInformationDTO) throws IOException {
        log.debug("REST request to save AddInformation: {}", fileAddInformationDTO);
        AddInformationDTO savedInformation = addInformationService.saveAddInformation(fileAddInformationDTO);
        return new ResponseEntity<>(savedInformation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddInformationDTO> updateAddInformation(
            @PathVariable Long id,
            @ModelAttribute FileAddInformationDTO fileAddInformationDTO) throws IOException {

        AddInformationDTO updatedAddInformation = addInformationService.uploadAddInformationPicture(id, fileAddInformationDTO);

        return ResponseEntity.ok(updatedAddInformation);
    }

    @GetMapping
    public List<AddInformationDTO> getAllAddInformation() {
        log.info("REST Request to get all Students");
        return addInformationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        log.debug("REST request to get one by id: {}", id);
        return new ResponseEntity<>(addInformationService.findOneById(id),HttpStatus.OK );
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug){
        log.debug("REST request to get one by slug: {}", slug);
        return new ResponseEntity<>(addInformationService.findOneBySlug(slug),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void deleteAddInformation(@PathVariable Long id) {
        log.info("REST Request to delete addInformation : {}", id);
        addInformationService.delecte(id);
    }

}
