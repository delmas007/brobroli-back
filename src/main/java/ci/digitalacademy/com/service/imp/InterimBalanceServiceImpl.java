package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.InterimBalance;
import ci.digitalacademy.com.repository.InterimBalanceRepository;
import ci.digitalacademy.com.service.InterimBalanceService;
import ci.digitalacademy.com.service.dto.InterimBalanceDTO;
import ci.digitalacademy.com.service.mapper.InterimBalanceMapper;
import ci.digitalacademy.com.utils.SlugifyUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@RequiredArgsConstructor
@Service
@Slf4j
public class InterimBalanceServiceImpl implements InterimBalanceService {

    private final InterimBalanceRepository interimBalanceRepository;
    private final InterimBalanceMapper interimBalanceMapper;

    @Override
    public InterimBalanceDTO save(InterimBalanceDTO interimBalanceDTO) {
        log.debug("Saving new interim balance: {}", interimBalanceDTO);
        InterimBalance interimBalance = interimBalanceMapper.toEntity(interimBalanceDTO);
        interimBalance = interimBalanceRepository.save(interimBalance);
        return interimBalanceMapper.fromEntity(interimBalance);
    }

    @Override
    public InterimBalanceDTO saveInterimBalance(InterimBalanceDTO interimBalanceDTO) {
        log.debug("Saving new interim balance: {}", interimBalanceDTO);
        interimBalanceDTO.setSlug(SlugifyUtils.generate("azertmlkjh"));

        InterimBalance interimBalance = interimBalanceMapper.toEntity(interimBalanceDTO);
        interimBalance = interimBalanceRepository.save(interimBalance);
        return interimBalanceMapper.fromEntity(interimBalance);
    }

    @Override
    public List<InterimBalanceDTO> findAll() {
        log.debug("Finding all interim balances");
        return interimBalanceRepository.findAll().stream().map(interimBalance ->{
            return interimBalanceMapper.fromEntity(interimBalance);
        }).toList();
    }


    @Override
    public InterimBalanceDTO update(Long id, InterimBalanceDTO interimBalanceDTO) {
        log.debug("Updating interim balance: {}", id);
        interimBalanceDTO.setId(Long.valueOf(id));
        return update(interimBalanceDTO);
    }

    @Override
    public InterimBalanceDTO update(InterimBalanceDTO interimBalanceDTO) {
        log.debug("Updating interim balance: {}", interimBalanceDTO);
        return interimBalanceRepository.findById(interimBalanceDTO.getId())
                .map(existingBalance -> {
                    if (interimBalanceDTO.getSum() != null) {
                        existingBalance.setSum(interimBalanceDTO.getSum());
                    }
                    if (interimBalanceDTO.getSlug() != null) {
                        existingBalance.setSlug(interimBalanceDTO.getSlug());
                    }
                    return save(interimBalanceMapper.fromEntity(existingBalance));
                })
                .orElse(null);
    }

    @Override
    public Optional<InterimBalanceDTO> getById(Long id) {
        log.debug("Finding interim balance by id: {}", id);
        return interimBalanceRepository.findById(id).map(customer ->
                interimBalanceMapper.fromEntity(customer));
    }

    @Override
    public Optional<InterimBalanceDTO> getBySlug(String slug) {
        log.debug("Requesting interim balance by slug: {}", slug);
        return interimBalanceRepository.findBySlug(slug)
                .map(interimBalanceMapper::fromEntity);
    }
}
