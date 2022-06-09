package ru.anpopov.privatevpn.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@RestController
@Slf4j
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final Environment environment;

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    void started(ApplicationReadyEvent event) {
        log.info(String.valueOf(InetAddress.getLocalHost().getHostAddress()));
    }

    @GetMapping
    public ResponseEntity<String> test(HttpServletRequest request) {
        log.info(String.valueOf(request));
        return ResponseEntity.ok("test complete");
    }
}
