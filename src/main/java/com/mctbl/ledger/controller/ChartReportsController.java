package com.mctbl.ledger.controller;

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
@RequestMapping("/data")
public class ChartReportsController {

	private static final String YEAR = "year";

	private static final String MONTH = "month";

	private static final String USER_ID = "userId";

	private static final String START_DATE = "startDate";

	private static final String END_DATE = "endDate";

	private static final String IS_CONSUME = "isConsume";

	@Autowired
	BillService bs;

	@Autowired
	CategoryService cs;

	@GetMapping("/pie/{userId}/{year}/{month}/{isConsume}")
	public Result<Map<String, Object>> getPieData(@PathVariable(USER_ID) Integer userId,
			@PathVariable(YEAR) Integer year, @PathVariable(MONTH) Integer month, @PathVariable(IS_CONSUME) Integer isConsume) {
		List<Bill> oneUserAllBillsWithYearAndMonth = bs.getOneUserAllBillsWithYearAndMonth(userId, null, year, month, isConsume);
		Map<Integer, Category> idCategoryMap = cs.getIdCategoryMap();

		Map<String, Map<String, Object>> dateMap = oneUserAllBillsWithYearAndMonth.stream()
				.collect(Collectors.groupingBy(Bill::getBillYMD)).entrySet().stream()
				.collect(Collectors.toMap(s -> s.getKey(),
						s -> s.getValue().stream()
								.collect(Collectors.toMap(b -> idCategoryMap.get(b.getCategoryId()).getCategoryName(),
										b -> b.getAmount().doubleValue(),
										(oldAmount, newAmount) -> (double) oldAmount + (double) newAmount))));
		List<String> categoryNameList = oneUserAllBillsWithYearAndMonth.stream().map(Bill::getCategoryId).distinct()
				.map(id -> idCategoryMap.get(id).getCategoryName()).collect(Collectors.toList());

		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("dateMap", dateMap);
		returnData.put("categoryNameList", categoryNameList);
		return Result.success(returnData);
	}

	@GetMapping("/bar/{userId}/{startDate}~{endDate}/{isConsume}")
	public Result<Map<String, Object>> getBarData(@PathVariable(USER_ID) Integer userId,
			@PathVariable(START_DATE) String startDate, @PathVariable(END_DATE) String endDate, @PathVariable(IS_CONSUME) Integer isConsume) {
		Map<Integer, Category> idCategoryMap = cs.getIdCategoryMap();
		List<Bill> oneUserAllBillsInRange = bs.getOneUserAllBillsInRangeMonth(userId, startDate, endDate, isConsume);

		Map<String, Map<Object, Object>> dateMap = oneUserAllBillsInRange.stream().collect(Collectors.groupingBy(Bill::getBillYM)).entrySet().stream().collect(Collectors.toMap(s -> s.getKey(),
				s -> s.getValue().stream()
				.collect(Collectors.toMap(b -> idCategoryMap.get(b.getCategoryId()).getCategoryName(),
						b -> b.getAmount().doubleValue(),
						(oldAmount, newAmount) -> (double) oldAmount + (double) newAmount))));
		List<String> categoryNameList = oneUserAllBillsInRange.stream().map(Bill::getCategoryId).distinct()
				.map(id -> idCategoryMap.get(id).getCategoryName()).collect(Collectors.toList());
		List<String> YMList = oneUserAllBillsInRange.stream().map(Bill::getBillYM).distinct().sorted().collect(Collectors.toList());

		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("dateMap", dateMap);
		returnData.put("categoryNameList", categoryNameList);
		returnData.put("YMList", YMList);
		return Result.success(returnData);
	}

	@GetMapping("/waterfall/{userId}/{startDate}~{endDate}")
	public Result<Map<String, Object>> getBarData(
			@PathVariable(USER_ID) Integer userId,
			@PathVariable(START_DATE) String startDate,
			@PathVariable(END_DATE) String endDate){
		List<Bill> oneUserAllBillsInRange = bs.getOneUserAllBillsInRangeDate(userId, startDate, endDate);
		List<String> YMDList = oneUserAllBillsInRange.stream().map(Bill::getBillYMD).distinct().sorted().collect(Collectors.toList());
		Map<String, Object> eachDayBill = oneUserAllBillsInRange.stream().collect(Collectors.groupingBy(Bill::getBillYMD,
				Collectors.teeing(            // 收入：isConsume = false，求和
			            Collectors.filtering(b -> !b.isConsume(),
			                    Collectors.summingDouble(b -> b.getAmount().doubleValue())),
			                Collectors.filtering(b -> b.isConsume(),
			                    Collectors.summingDouble(b -> -b.getAmount().doubleValue())),
			                (income, expense) -> new double[]{income, expense})));


		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("eachDayBill", eachDayBill);
		returnData.put("YMDList", YMDList);
		return Result.success(returnData);
	}

}
