package ci.digitalacademy.com.service.imp;


import ci.digitalacademy.com.service.NotificationMailService;
import ci.digitalacademy.com.service.dto.CollaborationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationMailServiceImp implements NotificationMailService {
    private final JavaMailSender javaMailSender;


    @Override
    public void sendNotificationMailCollaborationAttente(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Nouvelle demande de collaboration - " + collaborationDTO.getService().getTypeService());

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Nouvelle demande de collaboration</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong>,<br><br>" +
                    "                Vous avez reçu une nouvelle demande de collaboration de la part de <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong>.<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service demandé :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description du service :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Client :</strong> " + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "<br>" +
                    "                    <strong>Statut de la demande :</strong> " + collaborationDTO.getStatus() + "<br>" +
                    "                    <strong>Date de création :</strong> " + collaborationDTO.getCreateAt() + "<br>" +
                    "                </div>" +
                    "                Veuillez consulter votre espace pour accepter ou refuser cette demande.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendNotificationMailCollaborationAccepter(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Votre demande de collaboration a été acceptée");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Collaboration acceptée</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong>,<br><br>" +
                    "                Votre demande de collaboration pour le service suivant a été acceptée par <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong> :<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                Vous pouvez maintenant organiser les détails de la collaboration directement avec le prestataire.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendNotificationMailCollaborationRefuser(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Votre demande de collaboration a été refusée");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Collaboration refusée</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong>,<br><br>" +
                    "                Nous sommes désolés de vous informer que votre demande de collaboration pour le service suivant a été refusée par <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong> :<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                Nous vous invitons à consulter d'autres prestataires disponibles pour répondre à votre besoin.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendNotificationMailCollaborationAnnuler(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Annulation de la collaboration par le client");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Collaboration annulée</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong>,<br><br>" +
                    "                Nous vous informons que la collaboration concernant le service suivant a été annulée par le client <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong> :<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                Nous espérons que vous trouverez bientôt de nouvelles collaborations.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendNotificationMailCollaborationTerminerCustomer(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getService().getProvider().getEmail());
            helper.setSubject("Collaboration terminée par le client");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Collaboration terminée</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong>,<br><br>" +
                    "                Nous vous informons que le client <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong> a signalé que la collaboration concernant le service suivant est terminée :<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                Merci pour votre collaboration avec <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong>.<br>" +
                    "                Veuillez prendre contact avec le client si nécessaire.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendNotificationMailCollaborationTerminerProvider(CollaborationDTO collaborationDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(collaborationDTO.getCustomer().getEmail());
            helper.setSubject("Collaboration terminée par le prestataire");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #FBFBFB; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #FFFFFF; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #14C0B7;\">" +
                    "            <div style=\"background-color: #14C0B7; color: white; padding: 20px; font-size: 18px; text-align: center;\">Collaboration terminée</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + collaborationDTO.getCustomer().getFirstName() + " " + collaborationDTO.getCustomer().getLastName() + "</strong>,<br><br>" +
                    "                Nous vous informons que le prestataire <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong> a signalé que la collaboration concernant le service suivant est terminée :<br>" +
                    "                <br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Service :</strong> " + collaborationDTO.getService().getTypeService() + "<br>" +
                    "                    <strong>Description :</strong> " + collaborationDTO.getService().getDescription() + "<br>" +
                    "                    <strong>Tarif :</strong> " + collaborationDTO.getService().getPrice() + " fcfa<br>" +
                    "                    <strong>Durée estimée :</strong> " + collaborationDTO.getService().getDuration() + " heures<br>" +
                    "                </div>" +
                    "                Merci pour votre confiance et collaboration avec <strong>" + collaborationDTO.getService().getProvider().getFirstName() + " " + collaborationDTO.getService().getProvider().getLastName() + "</strong>.<br>" +
                    "                Si vous avez des remarques ou des questions, n'hésitez pas à nous contacter.<br>" +
                    "            </div>" +
                    "            <div style=\"background-color: #D3FCF9; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Brobroli Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
