package ru.anpopov.privatevpn.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UdpConfig {

    @Bean
    public IntegrationFlow processUniCastUdpMessageHttps(Environment environment) {
        int port = Integer.parseInt(environment.getProperty("server.port"));
        log.info("UDP is on {}", port);
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(port))
                .handle("udpService", "handleMessage")
                .get();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        try {
            while (true) {

            }
        } catch (Exception e) {
            log.info("Possibly stopping. Good bye, world!");
        }
    }
}
