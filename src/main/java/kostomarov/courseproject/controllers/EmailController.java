package kostomarov.courseproject.controllers;

import jakarta.servlet.http.HttpSession;
import kostomarov.courseproject.models.User;
import kostomarov.courseproject.services.EmailService;
import kostomarov.courseproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final UserService userService;

    @GetMapping("/sendMail")
    public String sendLoginMail(HttpSession session) {
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        String email = (String) session.getAttribute("email");

        String subject = "Ваші дані для входу";
        String text = String.format("Ваші дані для входу в систему WFM:\nЛогін: %s\nПароль: %s", login, password);
        emailService.sendTextMail("zkostomarov7@gmail.com", subject, text);

        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("email");

        return "redirect:/main";
    }
}
