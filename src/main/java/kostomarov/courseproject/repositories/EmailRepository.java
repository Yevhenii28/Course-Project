package kostomarov.courseproject.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository {
    void sendTextMail(String to, String subject, String text);
    void sendMailWithAttachment(String to, String subject, String text, String pathToAttachment);
}

