package com.mctbl.ledger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.mctbl.ledger.service.UsersService;


@MapperScan("com.mctbl.ledger.mapper")
@SpringBootApplication()
public class LedgerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LedgerApplication.class, args);
		UsersService bean = run.getBean(UsersService.class);
		System.out.println(bean.getAllUsers());
	}

}
