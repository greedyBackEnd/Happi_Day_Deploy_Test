package com.happiday.Happi_Day.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 식별 ID

    // 제약사항 추가
    @Column(name = "email", nullable = false, unique = true)
    private String email; // 이메일형식(중복 X)
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickName; // 닉네임(중복 X)
    @Column(name = "username", nullable = false)
    private String userName; // 실명

    @Column(name = "phone", nullable = false, unique = true)
    private String phone; // 전화번호(중복 X)

    @Column(name = "created_at", nullable = false)
    private Date createdAt; // 회원가입 날짜
    @Column(name = "update_at", nullable = false)
    private Date updateAt; // 회원정보 수정 날짜

    @Column(name = "role", nullable = false, columnDefinition = "user")
    private Role role; // 회원구분(default => user[value])

    @Column(name = "is_active", nullable = false, columnDefinition = "true")
    private boolean isActive; // 활성화 상태구분(default => true)

    @Column(name = "is_deleted", nullable = false, columnDefinition = "false")
    private boolean isDeleted; // 탈퇴, 관리자에 의한 삭제 구분(default => false)

    @Column(name = "last_login_at", nullable = true)
    private Date lastLoginAt; // 마지막 로그인 날짜
}
