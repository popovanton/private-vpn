package ru.anpopov.privatevpn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@Configuration
public class UdpConfig {

    @Bean
    public IntegrationFlow processUniCastUdpMessageHttps() {
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(443))
                .handle("udpService", "handleMessage")
                .get();
    }

    @Bean
    public IntegrationFlow processUniCastUdpMessage() {
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(80))
                .handle("udpService", "handleMessage")
                .get();
    }
}
