package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mctbl.ledger.bean.Bill;

public interface BillMapper {

	@Select("SELECT * from `bill`")
	List<Bill> getAllBills();

	@Select("SELECT * from `bill` where id=#{billsId} LIMIT 1")
	Bill getBillsById(@Param("billsId") Integer billsId);

	@Insert("INSERT INTO `bill` (id, user_id, category_id, amount, is_consume, bill_date, bill_description) VALUES(null, #{userId}, #{categoryId}, #{amount}, #{isConsume}, #{billDate}, #{billDescription})")
	int addNewBills(Bill newBills);

	@Update("UPDATE `bill` SET `category_id`=#{categoryId},`amount`=#{amount},`is_consume`=#{isConsume},`bill_date`=#{billDate},`bill_description`=#{billDescription} WHERE id=#{id}")
	int updateBills(Bill newBills);

	@Delete("DELETE FROM `bill` WHERE `id`=#{id} AND `user_id`=#{userId}")
	int deleteBills(Bill newBills);

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
