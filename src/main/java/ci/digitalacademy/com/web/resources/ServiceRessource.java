package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.ServiceService;
import ci.digitalacademy.com.service.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@Slf4j
@RequiredArgsConstructor
public class ServiceRessource {
    private final ServiceService serviceService;

    @GetMapping("/{typeService}/{minPrice}/{maxPrice}")
    public ResponseEntity<?> getAll(@PathVariable String typeService,@PathVariable Float minPrice,@PathVariable Float maxPrice) {
        log.debug("REST, Request to get Customers : {}", typeService);
        List<ServiceDTO> serviceDTOS = serviceService.searchServicesByTypeAndPriceRange(typeService, minPrice, maxPrice);
        if (serviceDTOS != null) {
            return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/typeService/{typeService}")
    public ResponseEntity<?> getAllService(@PathVariable String typeService) {
        log.debug("REST, Request to get all Customers : {}", typeService);
        List<ServiceDTO> serviceDTOS = serviceService.searchServicesByType(typeService);
        if (serviceDTOS != null) {
            return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
