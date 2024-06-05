package kostomarov.courseproject.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component("generator")
public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$_.@";
    private static final int PASSWORD_LENGTH = 8;

    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int idx = random.nextInt(CHARACTERS.length());
            sb.append(idx);
        }
        return sb.toString();
    }
}
