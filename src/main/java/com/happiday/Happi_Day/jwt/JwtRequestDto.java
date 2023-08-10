package com.happiday.Happi_Day.jwt;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String email;
    private String password;
}
