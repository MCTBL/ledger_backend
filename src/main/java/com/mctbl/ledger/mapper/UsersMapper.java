package com.mctbl.ledger.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Select;

import com.mctbl.ledger.bean.Users;

import jakarta.validation.constraints.NotNull;

public interface UsersMapper {

	@Select("SELECT * from `users`")
	List<Users> getAllUsers();

	Optional<Users> getOneUsersBy(@NotNull String userName);

}
