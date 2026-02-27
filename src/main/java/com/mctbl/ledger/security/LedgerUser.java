package com.mctbl.ledger.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mctbl.ledger.bean.Users;

@SuppressWarnings("serial")
public class LedgerUser implements UserDetails {

	private final Users us;
	private final Collection<? extends GrantedAuthority> authorities;

	public LedgerUser(Users us) {
		this.us = us;
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(us.getRole() == 1 ? "admin" : "user"));
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.us.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return this.us.getUserName();
	}

	public int getUserId() {
		return this.us.getId();
	}

}
