package findme_backend.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendWelcomeEmail(String to, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Welcome to FindMe");

        message.setText(
                "Hi " + name + ",\n\n" +
                        "Welcome to FindMe.\n\n" +
                        "Your account has been created successfully.\n\n" +
                        "Thank you."
        );

        mailSender.send(message);
    }

    @Override
    public void sendLoginEmail(String to, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Login Alert");

        message.setText(
                "Hi " + name + ",\n\n" +
                        "You have successfully logged into your FindMe account.\n\n" +
                        "If this wasn't you, please reset your password immediately.\n\n" +
                        "Regards,\nFindMe Team"
        );

        mailSender.send(message);
    }
}
