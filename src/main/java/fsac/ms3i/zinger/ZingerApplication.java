package fsac.ms3i.zinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ZingerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZingerApplication.class, args);
	}

}
