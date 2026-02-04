package com.mctbl.ledger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Users;

public interface UsersMapper {

	@Select("SELECT * from `users`")
	List<Users> getAllUsers();

}
