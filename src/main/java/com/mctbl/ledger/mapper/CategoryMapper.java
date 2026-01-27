package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Category;

public interface CategoryMapper {

	@Select("SELECT * from `category`")
	List<Category> getAllCategory();
	
}
