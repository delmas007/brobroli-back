package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.*;
import ci.digitalacademy.com.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/providers")
@Slf4j
@RequiredArgsConstructor
public class ProviderResource {

    private final ProviderService providerService;
    private final SkillService skillService;
    private final ServiceService serviceService;
    private final CollaborationService collaborationService;
    private final AddInformationService addInformationService;
    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<ProviderDTO> saveProvider(@ModelAttribute FileProviderDTO fileProviderDTO) throws IOException {
        log.debug("REST request to save provider: {}", fileProviderDTO);
        return new ResponseEntity<>(providerService.saveProvider(fileProviderDTO), HttpStatus.CREATED);
    }

    @PostMapping("/addInformation")
    public ResponseEntity<AddInformationDTO> saveAddInformation(
            @ModelAttribute FileAddInformationDTO fileAddInformationDTO) throws IOException {
        log.debug("REST request to save AddInformation: {}", fileAddInformationDTO);
        AddInformationDTO savedInformation = addInformationService.saveAddInformation(fileAddInformationDTO);
        return new ResponseEntity<>(savedInformation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ProviderDTO update(@ModelAttribute FileProviderDTO provider, @PathVariable Long id) throws IOException {
        log.debug("REST request to update: {}", provider);
        return providerService.update(provider, id);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        log.debug("REST request to get one by id: {}", id);
        return new ResponseEntity<>(providerService.findOneById(id),HttpStatus.OK );
    }
    @GetMapping("/userId/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable Long id){
        log.debug("REST request to get one by id: {}", id);
        return new ResponseEntity<>(providerService.findByUserId(id),HttpStatus.OK );
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug){
        log.debug("REST request to get one by slug: {}", slug);
        return new ResponseEntity<>(providerService.findOneBySlug(slug),HttpStatus.OK );
    }

    @GetMapping
    public List<ProviderDTO> findAll(){
        log.debug("REST request to find all");
        return providerService.findAll();
    }

    @PostMapping("/skill/{id}")
    public ResponseEntity<SkillDTO> savesSkill(@RequestBody SkillDTO skillDTO, @PathVariable Long id) {
        log.debug("Rest request to save skill: {}", skillDTO);
        return new ResponseEntity<>(skillService.saveskill(skillDTO,id), HttpStatus.CREATED);
    }

    @GetMapping("/skill/id/{id}")
    public ResponseEntity<?> getSkillById(@PathVariable Long id) {
        log.debug("REST request to get one by skill: {}", id);
        return new ResponseEntity<>(skillService.findOneById(id),HttpStatus.OK);
    }

    @GetMapping("/skills_provider/{id}")
    public ResponseEntity<?> findAllByProviderId(@PathVariable Long id) {
        log.debug("REST request to get all by provider_id: {}", id);
        return new ResponseEntity<>(skillService.findAllByProviderId(id),HttpStatus.OK);
    }

    @PutMapping("/skill/{id}")
    public SkillDTO updateSkill(@RequestBody SkillDTO skillDTO, @PathVariable Long id) {
        log.debug("REST request to update skill: {}", skillDTO);
        return skillService.update(skillDTO, id);
    }

    @DeleteMapping("/skill/{id}")
    public void deleteskillById(@PathVariable Long id) {
        log.debug("REST request to delete one by skill: {}", id);
        skillService.deleteById(id);
    }

    @PostMapping("/service/{id}")
    public ResponseEntity<ServiceDTO> savesService(@RequestBody ServiceDTO serviceDTO,@PathVariable Long id) {
        log.debug("Rest request to save service: {}", serviceDTO);
        return new ResponseEntity<>(serviceService.saveService(serviceDTO,id), HttpStatus.CREATED);
    }

    @PutMapping("service/{id}")
    public ServiceDTO updateService(@RequestBody ServiceDTO serviceDTO, @PathVariable Long id){
        log.debug("REST request to update: {}", serviceDTO);
        return serviceService.update(serviceDTO, id);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        log.debug("REST request to get one by service: {}", id);
        return new ResponseEntity<>(serviceService.findOneById(id),HttpStatus.OK);
    }

    @DeleteMapping("/service/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        log.debug("REST request to delete one by service: {}", id);
        serviceService.deleteById(id);
    }

    @GetMapping("/service")
    public ResponseEntity<?> getServiceAll() {
        return new ResponseEntity<>(serviceService.findAll(),HttpStatus.OK);
    }

    @PutMapping("/collaboration/accept/{id_collaboration}")
    public void acceptCollaboration(@PathVariable long id_collaboration) {
        log.debug("REST, Request to accept Collaboration : {}", id_collaboration);
        collaborationService.accept(id_collaboration);
    }


    @PutMapping("/collaboration/reject/{id_collaboration}")
    public void rejectCollaboration(@PathVariable long id_collaboration) {
        log.debug("REST, Request to reject Collaboration : {}", id_collaboration);
        collaborationService.reject(id_collaboration);
    }

    @PutMapping("/collaboration/terminer/{id_collaboration}")
    public void completeCollaboration(@PathVariable long id_collaboration) {
        log.debug("REST, Request to complete Collaboration : {}", id_collaboration);
        collaborationService.CompleteProvider(id_collaboration);
    }



    @PutMapping("/addInformation/{id}")
    public ResponseEntity<AddInformationDTO> updateAddInformation(
            @PathVariable Long id,
            @ModelAttribute FileAddInformationDTO fileAddInformationDTO) throws IOException {

        AddInformationDTO updatedAddInformation = addInformationService.uploadAddInformationPicture(id, fileAddInformationDTO);

        return ResponseEntity.ok(updatedAddInformation);
    }

    @PutMapping("/retrait/{sum}/{id}")
    public Integer updateBalance(@PathVariable Float sum,@PathVariable Long id){
        return balanceService.retrait(id, sum);
    }

    @GetMapping("/collaboration/provider/{id_provider}")
    public List<CollaborationDTO> findAllByProvider(@PathVariable Long id_provider){
        log.debug("REST request to find all by provider_id: {}", id_provider);
        return collaborationService.getCollaborationsByProviderId(id_provider);
    }
}
