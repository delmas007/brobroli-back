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
    @PutMapping("/service/valid/{seriveId}")
    public void validSercice(@PathVariable long seriveId) {
        log.debug("REST, Request to accept Service : {}", seriveId);
        adminService.valid(seriveId);
    }
    @PutMapping("/service/reject/{seriveId}")
    public void rejectService(@PathVariable long seriveId) {
        log.debug("REST, Request to reject Service : {}", seriveId);
        adminService.reject(seriveId);
    }
}
