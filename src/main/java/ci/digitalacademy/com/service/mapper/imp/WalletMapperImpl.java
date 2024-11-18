package ci.digitalacademy.com.service.mapper.imp;

import ci.digitalacademy.com.model.Wallet;
import ci.digitalacademy.com.service.dto.WalletDTO;
import ci.digitalacademy.com.service.mapper.WalletMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletMapperImpl implements WalletMapper {
    private final ModelMapper modelMapper;

    @Override
    public WalletDTO fromEntity(Wallet entity) {
        return modelMapper.map(entity, WalletDTO.class);
    }

    @Override
    public Wallet toEntity(WalletDTO dto) {
        return modelMapper.map(dto, Wallet.class);
    }
}
