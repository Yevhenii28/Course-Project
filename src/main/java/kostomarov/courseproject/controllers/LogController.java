package kostomarov.courseproject.controllers;

import jakarta.servlet.http.HttpSession;
import kostomarov.courseproject.models.User;
import kostomarov.courseproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LogController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        String message = (String) session.getAttribute("message");
        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/change_password")
    public String changePassword(Model model) {
        return "changePassForm";
    }

    @GetMapping("/change_password/check")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model, HttpSession session) {
        User user = userService.findByUsername(username);
        if (user != null && newPassword.equals(confirmPassword) && !passwordEncoder.matches(newPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.editUser(user);
            session.setAttribute("message", "Пароль успішно змінено.");
            return "redirect:/login";
        } else if (user == null) {
            model.addAttribute("error", "Користувач з цим логіном не зареєстрован.");
            return "forward:/change_password";
        } else if (passwordEncoder.matches(newPassword, user.getPassword())) {
            model.addAttribute("error", "Цей пароль вже використовується цим користувачем.");
            return "forward:/change_password";
        } else {
            model.addAttribute("error", "Паролі не співпадають.");
            return "forward:/change_password";
        }
    }
}
