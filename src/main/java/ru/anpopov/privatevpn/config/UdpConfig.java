package ru.anpopov.privatevpn.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.StringJoiner;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UdpConfig {
    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        try (DatagramSocket serverSocket = new DatagramSocket(500)) {
            int count = 0;
            byte[] in = new byte[1024];
            while (true) {
                System.out.println("doing things... on 500 " + count++);
                DatagramPacket packetIn = new DatagramPacket(in, in.length);
                serverSocket.receive(packetIn);
                byte[] data = packetIn.getData();

                //actual payload starts on 42 in wireshark
                String spiInitiator = Hex.encodeHexString(Arrays.copyOfRange(data, 0, 8));
                String spiResponse = Hex.encodeHexString(Arrays.copyOfRange(data, 8, 16));

                System.out.println("so the initiator is " + spiInitiator);
                System.out.println("so the response is " + spiResponse);
            }
        } catch (Exception e) {
            log.info("Possibly stopping. Good bye, world!");
        }
    }

}
