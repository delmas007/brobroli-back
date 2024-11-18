package ci.digitalacademy.com.web.resources;

import ci.digitalacademy.com.service.WalletService;
import ci.digitalacademy.com.service.dto.WalletDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
@Slf4j
@RequiredArgsConstructor
public class WalletRessource {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletDTO> saveWallet(@RequestBody WalletDTO walletDTO) {
        log.debug("Rest request to save wallet: {}", walletDTO);
        return new ResponseEntity<>(walletService.saveWallet(walletDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public WalletDTO updateWallet(@RequestBody WalletDTO walletDTO, @PathVariable Long id) {
        log.debug("REST request to update wallet: {}", walletDTO);
        return walletService.update(walletDTO, id);
    }
}
