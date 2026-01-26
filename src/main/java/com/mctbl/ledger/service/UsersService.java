package com.mctbl.ledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mctbl.ledger.bean.Users;
import com.mctbl.ledger.mapper.UsersMapper;

@Service
public class UsersService {

	@Autowired
	private UsersMapper um;
	
	public List<Users> getAllUsers(){
		return um.getAllUsers();
	}
	
}
