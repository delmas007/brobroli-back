package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.AdminService;
import ci.digitalacademy.com.service.dto.NumberUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/adminResources")
@RequiredArgsConstructor
public class AdminResource {

    private final AdminService adminService;

    @GetMapping
    public NumberUserDTO numberListUser(){
        log.debug("REST request to find all");
        return adminService.numberListUser();
    }
    @PutMapping("/service/valid/{id_service}")
    public void validSercice(@PathVariable long id_service) {
        log.debug("REST, Request to accept Service : {}", id_service);
        adminService.valid(id_service);
    }
    @PutMapping("/service/reject/{id_service}")
    public void rejectService(@PathVariable long id_service) {
        log.debug("REST, Request to reject Service : {}", id_service);
        adminService.reject(id_service);
    }
}
