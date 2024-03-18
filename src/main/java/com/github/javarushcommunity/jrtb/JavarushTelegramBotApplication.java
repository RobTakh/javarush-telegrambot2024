package com.github.javarushcommunity.jrtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {
		"package com.github.javarushcommunity.jrtb",
		"org.telegram.telegrambots"
})
@EnableScheduling
public class JavarushTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavarushTelegramBotApplication.class, args);
	}

}
