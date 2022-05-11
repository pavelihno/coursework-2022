package ru.mirea.coursework.butchershop.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.services.UserService;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/index", "/home"})
    public String index(Model model, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());

        return "home";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(UserEntity userEntity, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.addUser(userEntity);
            model.addAttribute("message", "Пользователь добавлен");
            return "redirect:/login?success_signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorTitle", "Ошибка при регистрации");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }
}
