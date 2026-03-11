package com.mctbl.ledger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	private static final String BILLS_ID = "billsId";

	private static final String CATEGORY_NAME = "categoryName";

	@Autowired
	BillService bs;

	@Autowired
	CategoryService cs;

	@GetMapping("/get/{userId}")
	public Result<Map<String, Object>> getOneUserAllBills(@PathVariable(USER_ID) Integer userId){
		List<Category> allCategory = cs.getAllCategory();
		List<Bill> oneUserAllBills = bs.getOneUserAllBillsWithYearAndMonth(userId, null, null, null, null);

		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("bills", oneUserAllBills);
		returnData.put("categories", allCategory);
		return Result.success(returnData);
	}

	@DeleteMapping("/delete/{userId}/{billsId}")
	public Result<String> deleteOneUserBills(@PathVariable(USER_ID) Integer userId, @PathVariable(BILLS_ID) Integer billsId){
		Bill temp = bs.getBillsById(billsId);
		if(temp == null || temp.getUserId() != userId) {
			return Result.error("错误的用户ID与账单ID");
		}else {
			try {
			bs.deleteBills(temp);
			return Result.success();
			}catch(Exception e) {
				return Result.error("出问题了" + e.toString());
			}
		}
	}

	@PostMapping("/update/{categoryName}")
	public Result<String> updateOneUserBills(@RequestBody Bill newBill, @PathVariable(CATEGORY_NAME) String categoryName){
		if(categoryName != null && categoryName.length() != 0) {
			try {
				Category cat = cs.getCategoryByName(categoryName);
				if(cat != null) {
					newBill.setCategoryId(cat.getId());
				}else {
					cs.createNewCategory(Category.builder().categoryName(categoryName).build());

					newBill.setCategoryId(cs.getCategoryByName(categoryName).getId());
				}

				bs.updateBills(newBill);
				return Result.success();
			}catch(Exception e) {
				return Result.error("出问题了" + e.toString());
			}
		}else {
			return Result.error("categoryName出问题了");
		}
	}

	@PostMapping("/add/{categoryName}")
	public Result<String> addNewBillsForUser(@RequestBody Bill newBill, @PathVariable(CATEGORY_NAME) String categoryName){
		if(categoryName != null && categoryName.length() != 0) {
			try {
			Category cat = cs.getCategoryByName(categoryName);
			if(cat != null) {
				newBill.setCategoryId(cat.getId());
			}else {
				cs.createNewCategory(Category.builder().categoryName(categoryName).build());

				newBill.setCategoryId(cs.getCategoryByName(categoryName).getId());
			}
			bs.addNewBills(newBill);
			return Result.success();

			}catch(Exception e) {
				return Result.error("出问题了" + e.toString());
			}
		}else {
			return Result.error("categoryName出问题了");
		}
	}

}
