package com.springbootmysql.bookproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringbootAngularMysqlIntroController {

	@GetMapping("/status")
	public String text(){
		return "The Backend of 'SpringBoot, Angular and MySQL Introduction by Fahmid' is up and running!";
	}
}
