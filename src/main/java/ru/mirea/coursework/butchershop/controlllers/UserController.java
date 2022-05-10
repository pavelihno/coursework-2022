package ru.mirea.coursework.butchershop.controlllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.models.User;
import ru.mirea.coursework.butchershop.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAll(Model model) {
        val users = User.toModel(userService.getAll());
        model.addAttribute("users", users);
        return "home";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    Object add(UserEntity userEntity, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.addUser(userEntity);
            model.addAttribute("message", "Пользователь добавлен");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorTitle", "Пользователь не добавлен");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
    }
}
