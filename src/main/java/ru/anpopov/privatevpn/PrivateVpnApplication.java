package ru.anpopov.privatevpn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PrivateVpnApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivateVpnApplication.class, args);
	}

}
