package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.model.enume.CollaborationStatus;
import ci.digitalacademy.com.model.enume.CustomerStatusService;
import ci.digitalacademy.com.model.enume.ProviderStatusService;
import ci.digitalacademy.com.repository.CollaborationRepository;
import ci.digitalacademy.com.service.*;
import ci.digitalacademy.com.service.dto.*;
import ci.digitalacademy.com.service.mapper.CollaborationMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
@Slf4j
public class CollaborationServiceImpl implements CollaborationService {
    private final CollaborationRepository collaborationRepository;
    private final CollaborationMapper collaborationMapper;
    private final ServiceService serviceService;
    private final CustomerService customerService;
    private final InterimBalanceService interimBalanceService;
    private final BalanceService balanceService;
    private final NotificationMailService notificationMailService;

    @Override
    public CollaborationDTO save(Long id_service, Long id_customer) {
        log.debug("Request to save Collaboration");
        Optional<CustomerDTO> oneCustomer = customerService.findOneCustomer(id_customer);
        Optional<ServiceDTO> oneService = serviceService.findOneById(id_service);
        CustomerDTO customerDTO = oneCustomer.get();
        ServiceDTO serviceDTO = oneService.get();
        if(customerDTO.getBalance()==null || customerDTO.getBalance().getSum()<serviceDTO.getPrice()){
            return null;
        }else {
            CollaborationDTO collaborationDTO = getCollaborationDTO(serviceDTO, customerDTO);
            CollaborationDTO save = save(collaborationDTO);
            notificationMailService.sendNotificationMailCollaborationAttente(save);
            return save(save);
        }
    }

    @Override
    public CollaborationDTO save(CollaborationDTO collaborationDTO) {
        return collaborationMapper.fromEntity(collaborationRepository.save(collaborationMapper.toEntity(collaborationDTO)));
    }

    @Override
    public Optional<CollaborationDTO> findOne(Long id) {
        return collaborationRepository.findById(id).map(
                collaboration -> collaborationMapper.fromEntity(collaboration)
        );
    }

    @Override
    public void accept(Long id_collaboration) {
        log.debug("Request to accept Collaboration");
        Optional<CollaborationDTO> collaborationDTO = findOne(id_collaboration);
        if (collaborationDTO.isPresent()){
            CollaborationDTO collaboration = collaborationDTO.get();
            collaboration.setCustomerStatusService(CustomerStatusService.EN_COURS);
            collaboration.setProviderStatusService(ProviderStatusService.EN_COURS);
            collaboration.setStatus(CollaborationStatus.ACCEPTE);
            save(collaboration);
            notificationMailService.sendNotificationMailCollaborationAccepter(collaboration);
        }
    }

    @Override
    public void reject(Long id_collaboration) {
        Optional<CollaborationDTO> collaborationDTO = findOne(id_collaboration);
        if (collaborationDTO.isPresent()){
            CollaborationDTO collaboration = collaborationDTO.get();
            collaboration.setStatus(CollaborationStatus.REFUSE);
            save(collaboration);
            notificationMailService.sendNotificationMailCollaborationRefuser(collaboration);
        }
    }

    @Override
    public void annuler(Long id_collaboration) {
        Optional<CollaborationDTO> collaborationDTO = findOne(id_collaboration);
        if (collaborationDTO.isPresent()){
            CollaborationDTO collaboration = collaborationDTO.get();
            collaboration.setStatus(CollaborationStatus.ANNULER);
            save(collaboration);
            notificationMailService.sendNotificationMailCollaborationAnnuler(collaboration);
        }
    }

    private CollaborationDTO getCollaborationDTO(ServiceDTO serviceDTO, CustomerDTO customerDTO) {
        Float sumInterim = serviceDTO.getPrice();
        customerDTO.getBalance().setSum(customerDTO.getBalance().getSum()-sumInterim);
        customerService.save(customerDTO);
        InterimBalanceDTO interimBalanceDTO = new InterimBalanceDTO();
        interimBalanceDTO.setSum(sumInterim);
        interimBalanceDTO = interimBalanceService.save(interimBalanceDTO);
        CollaborationDTO collaborationDTO = new CollaborationDTO();
        collaborationDTO.setCreateAt(LocalDate.now());
        collaborationDTO.setStatus(CollaborationStatus.EN_ATTENTE);
        collaborationDTO.setCustomer(customerDTO);
        collaborationDTO.setService(serviceDTO);
        collaborationDTO.setInterimBalance(interimBalanceDTO);
        return collaborationDTO;
    }

    @Override
    public void CompleteProvider(Long id_collaboration) {
        Optional<CollaborationDTO> collaboration = findOne(id_collaboration);
        if (collaboration.isPresent()){
            CollaborationDTO collaborationDTO = collaboration.get();
            collaborationDTO.setProviderStatusService(ProviderStatusService.TERMINE);
            if (collaborationDTO.getCustomerStatusService()== CustomerStatusService.TERMINE){
                Float newSum = collaborationDTO.getService().getProvider().getBalance().getSum()
                        + (collaborationDTO.getInterimBalance().getSum() * 0.97f);
                collaborationDTO.getService().getProvider().getBalance().setSum(newSum);
                balanceService.save(collaborationDTO.getService().getProvider().getBalance());
                collaborationDTO.setStatus(CollaborationStatus.TERMINE);
            }
            save(collaborationDTO);
            notificationMailService.sendNotificationMailCollaborationTerminerProvider(collaborationDTO);
        }
    }

    @Override
    public void CompleteCustomer(Long id_collaboration) {
        Optional<CollaborationDTO> collaboration = findOne(id_collaboration);
        if (collaboration.isPresent()){
            CollaborationDTO collaborationDTO = collaboration.get();
            collaborationDTO.setCustomerStatusService(CustomerStatusService.TERMINE);
            if (collaborationDTO.getProviderStatusService()== ProviderStatusService.TERMINE){
                collaborationDTO.setStatus(CollaborationStatus.TERMINE);
                Float newSum = collaborationDTO.getService().getProvider().getBalance().getSum()
                        + (collaborationDTO.getInterimBalance().getSum() * 0.97f);
                collaborationDTO.getService().getProvider().getBalance().setSum(newSum);
                balanceService.save(collaborationDTO.getService().getProvider().getBalance());
            }
            save(collaborationDTO);
            notificationMailService.sendNotificationMailCollaborationTerminerCustomer(collaborationDTO);
        }
    }

    @Override
    public List<CollaborationDTO> findAllByProvider(Long id_provider) {
        return List.of();
    }

    public List<CollaborationDTO> getCollaborationsByProviderId(Long providerId) {
        return collaborationRepository.findAllByProviderId(providerId).stream().map(collaborationMapper::fromEntity).toList();
    }

    @Override
    public List<CollaborationDTO> getCollaborationsBycustomerId(Long id_customer) {
        return collaborationRepository.findAllByCustomerId(id_customer).stream().map(collaborationMapper::fromEntity).toList();
    }


}
