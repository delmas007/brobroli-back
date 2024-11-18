package ci.digitalacademy.com.service.imp;


import ci.digitalacademy.com.service.NotificationMailService;
import ci.digitalacademy.com.service.dto.CollaborationDTO;
import ci.digitalacademy.com.service.dto.ValidationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
@Service
@RequiredArgsConstructor
public class NotificationMailServiceImp implements NotificationMailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendNotificationMailCollaborationAttente(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Nouvelle demande de collaboration - " + collaborationDTO.getService().getTypeService());

            Context context = new Context();
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());
            context.setVariable("status", collaborationDTO.getStatus());
            context.setVariable("createAt", collaborationDTO.getCreateAt());

            String body = templateEngine.process("sendNotificationMailCollaborationAttente", context);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de collaboration", e);
        }
    }



    @Override
    public void sendNotificationMailCollaborationAccepter(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String fromAddress = "noreply@delmas-gpt.tech";
            message.setFrom(fromAddress);
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Votre demande de collaboration a été acceptée");

            Context context = new Context();
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());

            String body = templateEngine.process("sendNotificationMailCollaborationAccepter", context);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email d'acceptation", e);
        }
    }



    @Override
    public void sendNotificationMailCollaborationRefuser(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String fromAddress = "noreply@delmas-gpt.tech";
            message.setFrom(fromAddress);
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Votre demande de collaboration a été refusée");

            // Création du contexte pour Thymeleaf
            Context context = new Context();
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());

            // Utilisation du moteur de template Thymeleaf pour générer le contenu du mail
            String body = templateEngine.process("sendNotificationMailCollaborationRefuser", context);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de refus de collaboration", e);
        }
    }


    @Override
    public void sendNotificationMailCollaborationAnnuler(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String fromAddress = "noreply@delmas-gpt.tech";
            message.setFrom(fromAddress);
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Annulation de la collaboration par le client");

            // Création du contexte pour Thymeleaf
            Context context = new Context();
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());

            // Utilisation du moteur de template Thymeleaf pour générer le contenu du mail
            String body = templateEngine.process("sendNotificationMailCollaborationAnnuler", context);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email d'annulation de collaboration", e);
        }
    }



    @Override
    public void sendNotificationMailCollaborationTerminerCustomer(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Collaboration terminée par le client");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailCollaborationTerminerCustomer", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de collaboration terminée", e);
        }
    }



    @Override
    public void sendNotificationMailCollaborationTerminerProvider(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Collaboration terminée par le prestataire");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("customerFirstName", collaborationDTO.getCustomer().getFirstName());
            context.setVariable("customerLastName", collaborationDTO.getCustomer().getLastName());
            context.setVariable("providerFirstName", collaborationDTO.getService().getProvider().getFirstName());
            context.setVariable("providerLastName", collaborationDTO.getService().getProvider().getLastName());
            context.setVariable("typeService", collaborationDTO.getService().getTypeService());
            context.setVariable("description", collaborationDTO.getService().getDescription());
            context.setVariable("price", collaborationDTO.getService().getPrice());
            context.setVariable("duration", collaborationDTO.getService().getDuration());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailCollaborationTerminerProvider", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de collaboration terminée", e);
        }
    }


    @Override
    public void sendNotificationMailCustomer(ValidationDTO validation) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(validation.getCustomer().getEmail());
            helper.setSubject("Votre code d'activation");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("customerLastName", validation.getCustomer().getLastName());
            context.setVariable("code", validation.getCode());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailCustomer", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de confirmation de compte", e);
        }
    }


    @Override
    public void sendNotificationMailProvider(ValidationDTO validation) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(validation.getProvider().getEmail());
            helper.setSubject("Votre code d'activation");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("providerLastName", validation.getProvider().getLastName());
            context.setVariable("code", validation.getCode());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailProvider", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de confirmation de compte pour le fournisseur", e);
        }
    }


    @Override
    public void sendNotificationMailMotCustomer(ValidationDTO validation) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(validation.getCustomer().getEmail());
            helper.setSubject("Votre code de changement de mot de passe");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("customerFirstName", validation.getCustomer().getFirstName());
            context.setVariable("code", validation.getCode());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailMotCustomer", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de changement de mot de passe pour le client", e);
        }
    }


    @Override
    public void sendNotificationMailMotProvider(ValidationDTO validation) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            // Sender email
            String fromAddress = "noreply@delmas-gpt.tech";
            mail.setFrom(fromAddress);
            helper.setTo(validation.getProvider().getEmail());
            helper.setSubject("Votre code de changement de mot de passe");

            // Create context for Thymeleaf template
            Context context = new Context();
            context.setVariable("providerFirstName", validation.getProvider().getFirstName());
            context.setVariable("code", validation.getCode());

            // Use Thymeleaf engine to process the template and generate the email body
            String body = templateEngine.process("sendNotificationMailMotProvider", context);
            helper.setText(body, true);

            // Send the email
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email de changement de mot de passe pour le fournisseur", e);
        }
    }



}
