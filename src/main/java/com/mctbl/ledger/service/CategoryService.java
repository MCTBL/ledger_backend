package com.mctbl.ledger.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mctbl.ledger.bean.Category;
import com.mctbl.ledger.mapper.CategoryMapper;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryMapper cm;
	
	public List<Category> getAllCategory(){
		return cm.getAllCategory();
	}
	
	public Map<Integer, Category> getIdCategoryMap(){
		return cm.getAllCategory().stream().collect(Collectors.toMap(Category::getId, c -> c));
	}
	
	public Map<String, Category> getNameCategoryMap(){
		return cm.getAllCategory().stream().collect(Collectors.toMap(Category::getCategoryName, c -> c));
	}
	
}
