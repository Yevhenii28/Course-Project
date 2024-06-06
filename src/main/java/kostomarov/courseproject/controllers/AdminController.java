package kostomarov.courseproject.controllers;

import jakarta.servlet.http.HttpSession;
import kostomarov.courseproject.models.Role;
import kostomarov.courseproject.models.User;
import kostomarov.courseproject.services.UserService;
import kostomarov.courseproject.utils.PasswordGenerator;
import kostomarov.courseproject.utils.TransliterateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TransliterateUtils translite;
    private final PasswordGenerator generator;

    @GetMapping("/createUser")
    private String createUser(Model model) {
        User user = new User();
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        user.setRole(role);
        model.addAttribute("user", user);
        return "createUser";
    }

    @GetMapping("/createUser/check")
    public String checkUser(Model model, User user, HttpSession session) {
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Для цього користувача вже є обліковий запис.");
            model.addAttribute("user", user);
            return "forward:/createUser";
        } else {
            String login = translite.transliterate(user.getSurname() + user.getName());
            user.setUsername(login);
            String rawPassword = generator.generatePassword();
            String password = passwordEncoder.encode(rawPassword);
            user.setPassword(password);
            userService.createNewUser(user, user.getRole().getName());

            session.setAttribute("login", login);
            session.setAttribute("password", rawPassword);
            session.setAttribute("email", user.getEmail());

            return "redirect:/sendMail";
        }
    }
}
