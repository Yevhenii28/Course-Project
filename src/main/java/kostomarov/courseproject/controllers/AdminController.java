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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

            return "redirect:/sendLoginMail";
        }
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "changeUser";
    }

    @GetMapping("/editUser/check")
    public String checkEditUser(Model model, User user, HttpSession session, @RequestParam(name="noChange", required = false) Boolean noChange) {
        String login = translite.transliterate(user.getSurname() + user.getName());
        user.setUsername(login);
        String rawPassword = "";
        if (noChange == null) {
            rawPassword = generator.generatePassword();
            String password = passwordEncoder.encode(rawPassword);
            user.setPassword(password);
            userService.createNewUser(user, user.getRole().getName());
        }

        session.setAttribute("login", login);
        session.setAttribute("password", rawPassword);
        session.setAttribute("email", user.getEmail());

        return "redirect:/sendChangeMail";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return "redirect:/index";
    }
}
