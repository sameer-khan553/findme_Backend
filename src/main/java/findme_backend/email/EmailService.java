package findme_backend.email;

public interface EmailService {

    void sendWelcomeEmail(String to, String name);

    void sendLoginEmail(String to, String name);

}