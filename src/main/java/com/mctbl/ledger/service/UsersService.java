package com.mctbl.ledger.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public Map<Integer, Users> getIdUsersMap(){
		return um.getAllUsers().stream().collect(Collectors.toMap(Users::getId, c->c));
	}

	public Map<String, Users> getNameUsersMap(){
		return um.getAllUsers().stream().collect(Collectors.toMap(Users::getUserName, c->c));
	}

	public Users getUserByUserName(String name) {
		return um.getOneUsersBy(name);
	}

}
