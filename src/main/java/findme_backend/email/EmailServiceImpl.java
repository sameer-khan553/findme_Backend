package findme_backend.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendWelcomeEmail(String to, String name) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("Welcome to FindMe");

        mail.setText(
                "Hi " + name +
                        "\n\nWelcome to FindMe." +
                        "\nYour account has been created successfully." +
                        "\n\nThank you."
        );

        mailSender.send(mail);
    }

    @Override
    public void sendLoginEmail(String to, String name) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("New Login");

        mail.setText(
                "Hi " + name +
                        "\n\nYou have successfully logged into your account."
        );

        mailSender.send(mail);
    }
}
