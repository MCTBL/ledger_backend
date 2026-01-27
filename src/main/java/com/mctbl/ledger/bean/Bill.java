package com.mctbl.ledger.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

	int id;
	int userId;
	int categoryId;
	BigDecimal amount;
	Timestamp billDate;
	String billDescription;
	
}
