package com.letmecook.backend;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscordController {

    @GetMapping("/loginSuccess")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {

        System.out.println("Controller called!");

        if (oauth2User == null) {
            System.out.println("oauth2User is null!");
        } else {
            System.out.println("=== Discord User Info ===");
            oauth2User.getAttributes().forEach((k, v) -> System.out.println(k + " = " + v));
            System.out.println("=========================");
        }

        return "welcome";
    }
}
