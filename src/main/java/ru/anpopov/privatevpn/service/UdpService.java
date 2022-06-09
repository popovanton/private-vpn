package ru.anpopov.privatevpn.service;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class UdpService {
    public void handleMessage(Message message) {
        String data = new String((byte[]) message.getPayload());
        System.out.print(data);
    }
}