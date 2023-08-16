package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.user.CustomUserDetails;
import com.happiday.Happi_Day.domain.entity.user.RoleType;
import com.happiday.Happi_Day.domain.entity.user.dto.UserRegisterDto;
import com.happiday.Happi_Day.domain.entity.user.dto.UserResponseDto;
import com.happiday.Happi_Day.domain.entity.user.dto.UserUpdateDto;
import com.happiday.Happi_Day.domain.service.UserService;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getUser() {
        String username = SecurityUtils.getCurrentUsername();
        UserResponseDto myProfile = userService.getUserProfile(username);
        return new ResponseEntity<>(myProfile,HttpStatus.OK);
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdateDto dto) {
        String username = SecurityUtils.getCurrentUsername();
        UserResponseDto newProfile = userService.updateUserProfile(username, dto);
        return new ResponseEntity<>(newProfile,HttpStatus.OK);
    }

}
