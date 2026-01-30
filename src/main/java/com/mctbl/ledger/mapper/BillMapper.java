package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Bill;

public interface BillMapper {

	@Select("SELECT * from `bill`")
	List<Bill> getAllBills();

	List<Bill> getUserBills(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId);

}
