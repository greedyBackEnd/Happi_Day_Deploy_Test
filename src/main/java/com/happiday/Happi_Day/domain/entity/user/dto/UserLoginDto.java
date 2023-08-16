package com.happiday.Happi_Day.domain.entity.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDto {
    @Email
    private String username;
    private String password;

}
