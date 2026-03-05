package com.letmecook.backend.user;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.letmecook.backend.JwtUtils;

@Controller
public class DiscordController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public DiscordController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User) {

        if (oauth2User == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Object idObj = oauth2User.getAttributes().get("id");
        Object globalNameObj = oauth2User.getAttributes().get("global_name");
        Object avatarObj = oauth2User.getAttributes().get("avatar");

        if (idObj == null || globalNameObj == null) {
            return ResponseEntity.status(400).body("Missing user attributes");
        }

        String idStr = idObj.toString();
        String globalName = globalNameObj.toString();
        String avatarId = avatarObj != null ? avatarObj.toString() : null;

        OAuth2UserDiscordDto dto = OAuth2UserDiscordDto.builder()
                .id(new BigInteger(idStr))
                .globalName(globalName)
                .avatarId(avatarId)
                .build();
        userService.createUser(dto);

        org.springframework.security.core.userdetails.UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(globalName)
                .password("") // no password, oauth user
                .authorities("ROLE_USER")
                .build();

        org.springframework.security.authentication.UsernamePasswordAuthenticationToken auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        String jwt = jwtUtils.generateJwtToken(auth);

        Map<String, Object> body = new HashMap<>();
        body.put("token", jwt);
        body.put("tokenType", "Bearer");
        body.put("expiresAt", jwtUtils.getExpirationDateFromJwtToken(jwt));

        return ResponseEntity.ok(body);
    }
}
