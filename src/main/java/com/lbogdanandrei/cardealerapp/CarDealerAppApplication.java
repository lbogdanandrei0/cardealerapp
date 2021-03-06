package com.lbogdanandrei.cardealerapp;

import com.lbogdanandrei.cardealerapp.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CarDealerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDealerAppApplication.class, args);
	}

}
