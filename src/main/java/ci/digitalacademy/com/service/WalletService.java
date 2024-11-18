package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.WalletDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface WalletService {

    WalletDTO save(WalletDTO walletDTO);
    WalletDTO partialUpdate(WalletDTO walletDTO, Long id);
    WalletDTO update(WalletDTO walletDTO, Long id);
    WalletDTO saveWallet(WalletDTO walletDTO);
    Optional<WalletDTO> findOneById(Long id);
}
