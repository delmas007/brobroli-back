package ci.digitalacademy.com.service;


import ci.digitalacademy.com.service.dto.CollaborationDTO;

public interface NotificationMailService {
    void sendNotificationMailCollaborationAttente(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationAccepter(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationRefuser(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationAnnuler(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationTerminerCustomer(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationTerminerProvider(CollaborationDTO collaborationDTO);
}
