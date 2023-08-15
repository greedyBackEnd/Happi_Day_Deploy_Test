package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
