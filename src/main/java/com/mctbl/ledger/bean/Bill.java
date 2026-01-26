package com.mctbl.ledger.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

	int id;
	int user_id;
	int category_id;
	BigDecimal amount;
	Timestamp bill_date;
	String bill_description;
	
}
