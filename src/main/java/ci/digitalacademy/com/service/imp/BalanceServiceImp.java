package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.Balance;
import ci.digitalacademy.com.repository.BalanceRepository;
import ci.digitalacademy.com.service.BalanceService;
import ci.digitalacademy.com.service.CustomerService;
import ci.digitalacademy.com.service.ProviderService;
import ci.digitalacademy.com.service.dto.BalanceDTO;
import ci.digitalacademy.com.service.dto.CustomerDTO;
import ci.digitalacademy.com.service.dto.ProviderDTO;
import ci.digitalacademy.com.service.mapper.BalanceMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class BalanceServiceImp implements BalanceService {
    private final BalanceRepository balanceRepository;
    private final CustomerService customerService;
    private final ProviderService providerService;
    private final BalanceMapper balanceMapper;
    @Override
    public BalanceDTO save(BalanceDTO balanceDTO, Long id) {
        Optional<CustomerDTO> customerDTO = customerService.findOneCustomer(id);
        if (customerDTO.isPresent()){
            CustomerDTO customer = customerDTO.get();
            if (customer.getBalance() == null){
                BalanceDTO balanceDTO1 = balanceMapper.fromEntity(balanceRepository.save(balanceMapper.toEntity(balanceDTO)));
                customer.setBalance(balanceDTO1);
                customerService.save(customer);
                return balanceDTO1;
            }else {
                return update(balanceDTO,customerDTO.get());
            }
        }else {
            return null;
        }
    }

    @Override
    public BalanceDTO save(BalanceDTO balanceDTO) {
        return balanceMapper.fromEntity(balanceRepository.save(balanceMapper.toEntity(balanceDTO)));
    }

    @Override
    public BalanceDTO update(BalanceDTO balanceDTO,CustomerDTO customerDTO) {
        Float sum = customerDTO.getBalance().getSum();
        BalanceDTO balance = customerDTO.getBalance();
        balance.setSum(sum + balanceDTO.getSum());
        return balanceMapper.fromEntity(balanceRepository.save(balanceMapper.toEntity(balance)));
    }

    @Override
    public BalanceDTO update(BalanceDTO userDTO, Long id) {
        return null;
    }

    @Override
    public List<BalanceDTO> findAll() {
        return List.of();
    }

    @Override
    public Optional<BalanceDTO> getById(Long id) {
        return balanceRepository.findById(id).map(balance -> {
            return balanceMapper.fromEntity(balance);
        });
    }

    @Override
    public Optional<BalanceDTO> getBySlug(String slug) {
        return Optional.empty();
    }

    @Override
    public Integer retrait(Long id, Float sum) {
        Optional<ProviderDTO> providerDTO = providerService.findOneById(id);
        if (providerDTO.isPresent()){
            ProviderDTO provider = providerDTO.get();
            if (provider.getBalance()!= null && provider.getBalance().getSum() >=sum){
                provider.getBalance().setSum(provider.getBalance().getSum()-sum);
                providerService.save(provider);
                return 1;
            }
            return 0;
        }
        return 3;
    }
}
