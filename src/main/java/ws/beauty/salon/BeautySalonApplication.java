package ws.beauty.salon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeautySalonApplication {

	public static void main(String[] args) {
	 System.setProperty("eureka.client.tls.enabled", "true");
        System.setProperty("eureka.client.tls.skipTlsValidation", "true");
	}

}
