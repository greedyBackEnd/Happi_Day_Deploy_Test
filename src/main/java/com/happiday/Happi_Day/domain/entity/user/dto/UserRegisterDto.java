package com.happiday.Happi_Day.domain.entity.user.dto;

import com.happiday.Happi_Day.domain.entity.user.RoleType;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRegisterDto {
    @Email
    private String username;
    private String password;
    private String nickname;
    private String realname;
    private String phone;
}
