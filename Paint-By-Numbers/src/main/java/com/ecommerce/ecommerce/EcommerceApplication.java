package com.ecommerce.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(EcommerceApplication.class, args);


		// PBNUtils.process("/static/images/ProcessingIMG/icon.png");



	}

}
