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

	public List<Bill> getOneUserAllBills(Integer userId, Integer categoryId) {
		return bm.getUserBills(userId, categoryId);
	}

}
