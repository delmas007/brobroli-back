package ci.digitalacademy.com.repository;

import ci.digitalacademy.com.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
