package kr.co.ch06;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("kr.co.ch06.persistence")
@SpringBootApplication
public class Ch06Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch06Application.class, args);
	}

}