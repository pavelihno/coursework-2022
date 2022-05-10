package ru.mirea.coursework.butchershop.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.coursework.butchershop.entities.AddressEntity;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.services.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/edit_main")
    public String getEditMainPage(Model model, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("user", user);
        return "edit_main";
    }

    @PostMapping("/edit_main")
    String editMainInfo(@AuthenticationPrincipal UserEntity user, UserEntity updatedUser, RedirectAttributes attrs) {
        profileService.updateMainInfo(user, updatedUser);
        return "redirect:/home";
    }

    @GetMapping("/deposit")
    public String getDepositPage(Model model, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        return "deposit";
    }

    @PostMapping("/deposit")
    String depositBalance(@AuthenticationPrincipal UserEntity user, UserEntity updatedUser, RedirectAttributes attrs) {
        profileService.depositBalance(user, updatedUser);
        return "redirect:/home";
    }

    @GetMapping("/edit_address")
    public String getEditAddressPage(Model model, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("address", user.getAddress());
        return "edit_address";
    }

    @PostMapping("/edit_address")
    String editAddressInfo(@AuthenticationPrincipal UserEntity user, AddressEntity newAddress, RedirectAttributes attrs) {
        profileService.editAddress(user, newAddress);
        return "redirect:/home";
    }
}