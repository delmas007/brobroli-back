package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.AdminService;
import ci.digitalacademy.com.service.dto.NumberUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
