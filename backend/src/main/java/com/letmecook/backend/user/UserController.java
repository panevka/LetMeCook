package com.letmecook.backend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmecook.backend.user.dto.GetUserDto;
import com.letmecook.backend.user.dto.PatchUserDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<GetUserDto> getUserProfile(@PathVariable Long userId) {
        var profile = userService.getUserProfile(userId);
        return ResponseEntity.ok().body(profile);
    }

    @PatchMapping("/{userId}")
    ResponseEntity<GetUserDto> updateUserProfile(@RequestBody PatchUserDto dto, @PathVariable Long userId) {
        var updatedUser = userService.updateUserProfile(userId, dto);
        return ResponseEntity.ok().body(updatedUser);
    }

}
