package com.mctbl.ledger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@MapperScan("com.mctbl.ledger.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LedgerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LedgerApplication.class, args);
//		JWTConfig bean = run.getBean(JWTConfig.class);
//		System.out.println(bean.secret);
	}

}
