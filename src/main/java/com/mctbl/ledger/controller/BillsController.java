package com.mctbl.ledger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mctbl.ledger.bean.Bill;
import com.mctbl.ledger.bean.Category;
import com.mctbl.ledger.bean.Result;
import com.mctbl.ledger.service.BillService;
import com.mctbl.ledger.service.CategoryService;

@RestController
@RequestMapping("/bills")
public class BillsController {

	private static final String USER_ID = "userId";

	@Autowired
	BillService bs;

	@Autowired
	CategoryService cs;

	@GetMapping("/get/{userId}")
	public Result<Map<String, Object>> getOneUserAllBills(@PathVariable(USER_ID) Integer userId){
		List<Category> allCategory = cs.getAllCategory();
		List<Bill> oneUserAllBills = bs.getOneUserAllBillsWithYearAndMonth(userId, null, null, null, null);

//		List<List<Object>> temp = bs.getOneUserAllBillsWithYearAndMonth(userId, null, null, null, null).stream()
//			.map(b -> Arrays.asList(new Object[] {b.getId(), b.getBillDate(), idCategoryMap.get(b.getCategoryId()), b.getAmount().doubleValue() * (b.isConsume() ? 1 : -1), b.getBillDescription()})).collect(Collectors.toList());

		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("bills", oneUserAllBills);
		returnData.put("categories", allCategory);
		return Result.success(returnData);
	}

}
