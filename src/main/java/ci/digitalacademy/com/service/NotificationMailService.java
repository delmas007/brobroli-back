package ci.digitalacademy.com.service;


import ci.digitalacademy.com.service.dto.CollaborationDTO;
import ci.digitalacademy.com.service.dto.ValidationDTO;

public interface NotificationMailService {
    void sendNotificationMailCollaborationAttente(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationAccepter(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationRefuser(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationAnnuler(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationTerminerCustomer(CollaborationDTO collaborationDTO);
    void sendNotificationMailCollaborationTerminerProvider(CollaborationDTO collaborationDTO);

    void sendNotificationMailCustomer(ValidationDTO validation);
    void sendNotificationMailProvider(ValidationDTO validation);

    void sendNotificationMailMotCustomer(ValidationDTO validation);
    void sendNotificationMailMotProvider(ValidationDTO validation);

}
