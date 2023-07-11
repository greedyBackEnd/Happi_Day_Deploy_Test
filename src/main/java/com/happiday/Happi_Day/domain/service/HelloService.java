package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.Hello;
import com.happiday.Happi_Day.domain.repository.HelloRepository;
import com.happiday.Happi_Day.exception.CustomException;
import com.happiday.Happi_Day.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HelloService {
    private final HelloRepository repository;

    public Hello save(Hello hello) {
        if (hello.getText() == null || hello.getText().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
        return repository.save(hello);
    }

    public Hello findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
    }
}
