package ci.digitalacademy.com.web.resources;


import ci.digitalacademy.com.service.InterimBalanceService;
import ci.digitalacademy.com.service.dto.InterimBalanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interim-balance")
public class InterimBalanceRessource {

    private final InterimBalanceService interimBalanceService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save interim balance")
    @Operation(summary = "Save new interim balance", description = "This endpoint allows to save an interim balance")
    public ResponseEntity<InterimBalanceDTO> saveInterimBalance(@RequestBody InterimBalanceDTO interimBalanceDTO) {
        log.debug("REST, Request to save Interim Balance: {}", interimBalanceDTO);
        return new ResponseEntity<>(interimBalanceService.saveInterimBalance(interimBalanceDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<InterimBalanceDTO> getAllInterimBalances() {
        log.debug("REST, Request to get all interim balances");
        return interimBalanceService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInterimBalance(@RequestBody InterimBalanceDTO interimBalanceDTO, @PathVariable Long id) {
        log.debug("REST, Request to update Interim Balance: {}, ID: {}", interimBalanceDTO, id);
        return new ResponseEntity<>(interimBalanceService.update(id, interimBalanceDTO), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInterimBalance(@PathVariable long id) {
        log.debug("REST, Request to get Interim Balance by ID: {}", id);
        Optional<InterimBalanceDTO> interimBalanceDTO = interimBalanceService.getById(id);
        if (interimBalanceDTO.isPresent()) {
            return new ResponseEntity<>(interimBalanceDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getInterimBalanceBySlug(@PathVariable String slug) {
        log.debug("REST, Request to get Interim Balance by slug: {}", slug);
        Optional<InterimBalanceDTO> interimBalanceDTO = interimBalanceService.getBySlug(slug);
        if (interimBalanceDTO.isPresent()) {
            return new ResponseEntity<>(interimBalanceDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
