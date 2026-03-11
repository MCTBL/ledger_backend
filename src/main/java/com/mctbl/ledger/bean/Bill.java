package com.mctbl.ledger.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	boolean isConsume;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Timestamp billDate;
	String billDescription;

	public String getBillYMD() {
		return this.billDate.toLocalDateTime().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	public String getBillYM() {
		return this.billDate.toLocalDateTime().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE).substring(0, 7);
	}

}
