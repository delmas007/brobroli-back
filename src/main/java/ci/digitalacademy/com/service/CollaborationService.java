package ci.digitalacademy.com.service;

import ci.digitalacademy.com.service.dto.CollaborationDTO;

import java.util.List;
import java.util.Optional;

public interface CollaborationService {
    CollaborationDTO save(Long id_service,Long id_customer);
    CollaborationDTO save(CollaborationDTO collaborationDTO);
    Optional<CollaborationDTO> findOne(Long id);
    void accept(Long id_collaboration);
    void reject(Long id_collaboration);
    void annuler(Long id_collaboration);
    void CompleteProvider(Long id_collaboration);
    void CompleteCustomer(Long id_collaboration);
    List<CollaborationDTO> findAllByProvider(Long id_provider);
    List<CollaborationDTO> getCollaborationsByProviderId(Long id_provider);
    List<CollaborationDTO> getCollaborationsBycustomerId(Long id_customer);
}
