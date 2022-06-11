package ru.anpopov.privatevpn.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UdpConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void steady() {
        try {
            int count = 0;
            while (true) {
                System.out.println("doing things..." + count++);
                try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(System.getProperty("PORT")));
                     Socket accept = serverSocket.accept()
                ) {
                    try (InputStream inputStream = accept.getInputStream();
                         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                         OutputStream outputStream = accept.getOutputStream();
                         BufferedReader br = new BufferedReader(inputStreamReader)) {
                        br.lines().forEach(System.out::println);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } catch (Exception e) {
            log.info("Possibly stopping. Good bye, world!");
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready(Environment environment) {
        if (Objects.nonNull(environment.getProperty("server.port"))) {
            try {
                int count = 0;
                while (true) {
                    System.out.println("doing things..." + count++);
                    try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(environment.getProperty("server.port")));
                         Socket accept = serverSocket.accept()
                    ) {
                        try (InputStream inputStream = accept.getInputStream();
                             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                             OutputStream outputStream = accept.getOutputStream();
                             BufferedReader br = new BufferedReader(inputStreamReader)) {
                            br.lines().forEach(System.out::println);
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            } catch (Exception e) {
                log.info("Possibly stopping. Good bye, world!");
            }
        }
    }
}
