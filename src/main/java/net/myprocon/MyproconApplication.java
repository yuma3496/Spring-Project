package net.myprocon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class MyproconApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyproconApplication.class, args);
	}

}
