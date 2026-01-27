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

	public List<Bill> getAllBill(){
		return bm.getAllBill();
	}
	
}
