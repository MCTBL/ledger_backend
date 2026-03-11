package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Category;

public interface CategoryMapper {

	@Select("SELECT * from `category`")
	List<Category> getAllCategory();

	@Select("SELECT * from `category` WHERE `category_name`=#{name} LIMIT 1")
	Category getCategoryByName(String name);

	@Select("SELECT * from `category` WHERE `id`=#{name} LIMIT 1")
	Category getCategoryById(Integer Id);

	@Insert("INSERT INTO `category` (id, category_name, category_description) VALUES (null, #{categoryName}, #{categoryDescription})")
	int addNewCategory(Category newCate);
}
