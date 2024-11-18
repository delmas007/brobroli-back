package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Wallet;
import ci.digitalacademy.com.repository.WalletRepository;
import ci.digitalacademy.com.service.WalletService;
import ci.digitalacademy.com.service.dto.WalletDTO;
import ci.digitalacademy.com.service.mapper.WalletMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public WalletDTO save(WalletDTO walletDTO) {
        Wallet wallet = walletMapper.toEntity(walletDTO);
        log.info("Saving wallet: {}", wallet);
        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.fromEntity(savedWallet);
    }

    @Override
    public WalletDTO partialUpdate(WalletDTO walletDTO, Long id) {
        return walletRepository.findById(id).map(existingwallet -> {
            if (walletDTO.getRising() != null) {
                existingwallet.setRising(walletDTO.getRising());
            }
            if (walletDTO.getMobileMoney() != null) {
                existingwallet.setMobileMoney(walletDTO.getMobileMoney());
            }
            return save(walletMapper.fromEntity(existingwallet));
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public WalletDTO update(WalletDTO walletDTO, Long id) {
        return findOneById(id).map(wallet->{
            wallet.setRising(walletDTO.getRising());
            wallet.setMobileMoney(walletDTO.getMobileMoney());
            return save(wallet);
        }).orElseThrow(()-> new IllegalArgumentException("Wallet not found !"));
    }

    @Override
    public WalletDTO saveWallet(WalletDTO walletDTO) {
        final String slug = SlugifyUtils.generate(walletDTO.getRising());
        walletDTO.setSlug(slug);
        return save(walletDTO);
    }

    @Override
    public Optional<WalletDTO> findOneById(Long id) {
        return walletRepository.findById(id).map(wallet->{
            return walletMapper.fromEntity(wallet);
        });
    }
}
