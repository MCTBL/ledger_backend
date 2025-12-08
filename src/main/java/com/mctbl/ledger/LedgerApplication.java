package com.mctbl.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.mctbl.ledger.config.JWTConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LedgerApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LedgerApplication.class, args);
//		JWTConfig bean = run.getBean(JWTConfig.class);
//		System.out.println(bean.secret);
	}
	
}
