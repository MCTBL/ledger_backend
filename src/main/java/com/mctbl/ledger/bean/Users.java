package com.mctbl.ledger.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	
	int id;
	String userName;
	String passwordHash;
	int role;
	
}
