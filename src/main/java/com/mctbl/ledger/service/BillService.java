package com.mctbl.ledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mctbl.ledger.bean.Bill;
import com.mctbl.ledger.mapper.BillMapper;

@Service
public class BillService {

	@Autowired
	private BillMapper bm;

	public List<Bill> getAllBills() {
		return bm.getAllBills();
	}

	public List<Bill> getOneUserAllBillsWithYearAndMonth(Integer userId, Integer categoryId, Integer year, Integer month, Integer isConsume) {
		return bm.getUserBills(userId, categoryId, year, month, isConsume);
	}

	public List<Bill> getOneUserAllBillsInRange(Integer userId, String startDate, String endDate, Integer isConsume) {
		return bm.getUserBillsInRange(userId, startDate, endDate, isConsume);
	}

}
