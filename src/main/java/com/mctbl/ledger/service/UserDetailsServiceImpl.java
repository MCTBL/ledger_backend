package com.mctbl.ledger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mctbl.ledger.bean.Users;
import com.mctbl.ledger.security.LedgerUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersService us;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = us.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("");
		}
        return new LedgerUser(user);
	}

}
