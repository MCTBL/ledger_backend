package com.mctbl.ledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mctbl.ledger.bean.Bill;
import com.mctbl.ledger.service.BillService;

@RestController
@RequestMapping("/pie")
public class PieReportsController {

	@Autowired
	BillService bs;

	@GetMapping("/{userId}/{year}/{month}")
	public List<Bill> getOneUserOneMonthAllBills(@PathVariable("userId") Integer userId,
			@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
		return bs.getOneUserAllBillsWithYearAndMonth(userId, null, year, month);
	}

}
