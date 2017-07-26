package com.heroku.carpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class CarproApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CarproApplication.class, args);
	}
}

/*
C:\Users\Administrator\Desktop\carpro>heroku config
=== fierce-tor-67008 Config Vars
DATABASE_URL: postgres://liquyobugrkwmp:b5b6a87692f7c8184fdd9e131c094e4a97c753338a0f168c6f4ca03198c0e182@ec2-184-73-199-72.compute-1.amazonaws.com:5432/d2dphbb17ob2hc

*/