package com.letmecook.backend.user;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscordController {

    UserService userService;

    public DiscordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {

        if (oauth2User == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        } else {
            String id = oauth2User.getAttributes().get("id").toString();
            String globalName = oauth2User.getAttributes().get("global_name").toString();
            String avatarId = oauth2User.getAttributes().get("avatar").toString();

            OAuth2UserDiscordDto dto = OAuth2UserDiscordDto.builder()
                    .id(new BigInteger(id))
                    .globalName(globalName)
                    .avatarId(avatarId)
                    .build();

            userService.createUser(dto);
        }

        return ResponseEntity.ok("Login Successful");
    }
}
