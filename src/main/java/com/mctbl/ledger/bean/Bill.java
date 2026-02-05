package com.mctbl.ledger.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

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
	int userId;
	int categoryId;
	BigDecimal amount;
	Timestamp billDate;
	String billDescription;

	public String getBillYMD() {
		return this.billDate.toLocalDateTime().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

}
