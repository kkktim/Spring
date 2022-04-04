package kr.co.ch01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch01Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch01Application.class, args);
	}
	
	@GetMapping("/hello")
	public void hello() {
		System.out.println("Hello World!");
	}
	@GetMapping("/welcome")
	public void welcome() {
		System.out.println("Welcome Spring!");
	}
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public void greeting() {
		System.out.println("Greeting Spring!");
	}
}
