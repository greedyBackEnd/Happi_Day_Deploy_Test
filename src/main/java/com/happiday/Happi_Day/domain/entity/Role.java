package com.happiday.Happi_Day.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    user("회원"),
    admin("슈퍼관리자");

    private String status;

    Role(String status) {
        this.status = status;
    }

    // 회원인지, 슈퍼관리자인지를 구분하기 위한 작업
    public String getStatus() {
        return this.status;
    }
}
