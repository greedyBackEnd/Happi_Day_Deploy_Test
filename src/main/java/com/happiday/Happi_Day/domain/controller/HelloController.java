package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.Hello;
import com.happiday.Happi_Day.domain.service.HelloService;
import com.happiday.Happi_Day.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HelloController {
    private final HelloService service;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/{id}")
    public String getHello(@PathVariable Long id, Model model) {
        try {
            Hello hello = service.findById(id);
            model.addAttribute("hello", hello);
        } catch (CustomException e) {
            model.addAttribute("error", e.getErrorCode().getMessage());
        }
        return "getHello";
    }

    @GetMapping("/save")
    public String saveHello(Model model) {
        Hello hello = Hello.builder()
                .text("hell text")
                .build();
        try {
            Hello savedHello = service.save(hello);
            model.addAttribute("savedHello", savedHello);
        } catch (CustomException e) {
            model.addAttribute("error", e.getErrorCode().getMessage());
        }
        return "saveResult";
    }
}
