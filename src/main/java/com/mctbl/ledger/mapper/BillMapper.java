package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Bill;

public interface BillMapper {

	@Select("SELECT * from `bill`")
	List<Bill> getAllBills();

	List<Bill> getUserBills(@Param("userId") Integer userId,
			@Param("categoryId") Integer categoryId,
			@Param("year") Integer year,
			@Param("month") Integer month,
			@Param("isConsume") Integer isConsume);

	List<Bill> getUserBillsInRangeMonth(@Param("userId") Integer userId,
			@Param("startMonth") String startMonth,
			@Param("endMonth") String endMonth,
			@Param("isConsume") Integer isConsume);

	@Select("SELECT * FROM `bill` WHERE user_id = #{userId} AND `bill_date` >= #{startDate} AND `bill_date` <= #{endDate} ORDER BY `bill_date`")
	List<Bill> getUserBillsInRangeDate(@Param("userId") Integer userId,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);

}
