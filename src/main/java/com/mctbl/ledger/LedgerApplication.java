package com.mctbl.ledger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("com.mctbl.ledger.mapper")
@SpringBootApplication
public class LedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedgerApplication.class, args);
	}

}
