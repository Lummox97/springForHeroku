package springForHeroku;

import Legacy.Inspector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringForHerokuApplication {

	@Bean
	Inspector init() {
		return new Inspector();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringForHerokuApplication.class, args);
	}

}
