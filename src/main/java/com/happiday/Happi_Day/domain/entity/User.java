package com.happiday.Happi_Day.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 식별 ID

    // 제약사항 추가
    @Column(nullable = false, unique = true)
    private String userEmail; // 이메일형식(중복 X)

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false, unique = true)
    private String nickName; // 닉네임(중복 X)
    @Column(nullable = false)
    private String userName; // 실명

    @Column(nullable = false, unique = true)
    private String phoneNumber; // 전화번호(중복 X)

    @Column(nullable = false)
    private String role; // 회원구분

    @Column(nullable = false)
    private Boolean isActive; // 활성화 상태구분(default => true)

    @Column(nullable = false)
    private Boolean isDeleted; // 탈퇴, 관리자에 의한 삭제 구분(default => false)

    private LocalDateTime lastLoginAt; // 마지막 로그인 날짜
}
