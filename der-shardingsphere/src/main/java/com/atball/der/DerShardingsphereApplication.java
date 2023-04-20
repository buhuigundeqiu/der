package com.atball.der;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.atball.der.shardingsphere.mapper")
@SpringBootApplication
public class DerShardingsphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(DerShardingsphereApplication.class, args);
	}

}
