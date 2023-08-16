package com.happiday.Happi_Day.domain.entity.user.dto;

import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String password;
    private String nickname;
    private String phone;

    public User toEntity() {
        return User.builder()
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .build();
    }
}
