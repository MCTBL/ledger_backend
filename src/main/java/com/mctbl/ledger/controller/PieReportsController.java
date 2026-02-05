package com.mctbl.ledger.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequestMapping("/pie")
public class PieReportsController {

	@Autowired
	BillService bs;

	@Autowired
	CategoryService cs;

	@GetMapping("/{userId}/{year}/{month}")
	public Result<Map<String, Object>> getOneUserOneMonthAllBills(@PathVariable("userId") Integer userId,
			@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
		List<Bill> oneUserAllBillsWithYearAndMonth = bs.getOneUserAllBillsWithYearAndMonth(userId, null, year, month);
		Map<Integer, Category> idCategoryMap = cs.getIdCategoryMap();
		Map<String, List<Bill>> dateBillMap = oneUserAllBillsWithYearAndMonth.stream()
				.collect(Collectors.groupingBy(Bill::getBillYMD));

		Map<String, Map<String, Object>> dateMap = dateBillMap.entrySet().stream()
				.collect(Collectors.toMap(s -> s.getKey(),
						s -> s.getValue().stream()
								.collect(Collectors.toMap(b -> idCategoryMap.get(b.getCategoryId()).getCategoryName(),
										b -> b.getAmount().doubleValue(),
										(oldAmount, newAmount) -> (double) oldAmount + (double) newAmount))));

		HashMap<String, Object> returnData = new HashMap<String, Object>();

		List<String> categoryNameList = oneUserAllBillsWithYearAndMonth.stream().map(Bill::getCategoryId).distinct().map(id->idCategoryMap.get(id).getCategoryName()).collect(Collectors.toList());

		returnData.put("dateMap", dateMap);
		returnData.put("categoryNameList", categoryNameList);
		

		return Result.success(returnData);
	}

}
