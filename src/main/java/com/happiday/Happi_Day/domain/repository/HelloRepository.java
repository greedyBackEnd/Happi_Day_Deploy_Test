package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {
}
