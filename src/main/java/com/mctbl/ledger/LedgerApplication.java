package com.mctbl.ledger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mctbl.ledger.service.BillService;
import com.mctbl.ledger.service.CategoryService;
import com.mctbl.ledger.service.UsersService;


@MapperScan("com.mctbl.ledger.mapper")
@SpringBootApplication
public class LedgerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LedgerApplication.class, args);
//		UsersService us = run.getBean(UsersService.class);
//		System.out.println(us.getAllUsers());
		BillService bs = run.getBean(BillService.class);
		System.out.println(bs.getOneUserAllBillsWithYearAndMonth(1, 1, 2024, null));
//		CategoryService cs = run.getBean(CategoryService.class);
//		System.out.println(cs.getIdCategoryMap());
	}

}
