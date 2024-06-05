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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TransliterateUtils translite;
    private final PasswordGenerator generator;

    @PostMapping("/createUser/check")
    public String checkUser(Model model, User user, HttpSession session) {
        /*if (!userService.existsByUsername(user.getUsername())) {
            model.addAttribute("user", user);
            return "createUser";
        }     */

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
