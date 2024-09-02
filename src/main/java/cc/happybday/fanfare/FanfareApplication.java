package cc.happybday.fanfare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FanfareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanfareApplication.class, args);
	}

}
