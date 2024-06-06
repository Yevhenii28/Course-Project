package kostomarov.courseproject.controllers;

import kostomarov.courseproject.models.Role;
import kostomarov.courseproject.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    private String index(Model model) {
        return "index";
    }


}
