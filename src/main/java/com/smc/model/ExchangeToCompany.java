package com.smc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smc.id.ExchangeCompanyId;

@Entity
@IdClass(ExchangeCompanyId.class)
@Table(name = "COMPANY_AND_EXCHANGE")
public class ExchangeToCompany {

	@Id
	@Column(name = "company_id")
	private long companyId;
	
	@Id
	@Column(name = "exchange_id")
	private long exchangeId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId
	private StockExchange exchange;
	
	@Column(name="stock_code", nullable = false)
	private String stockCode;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public StockExchange getExchange() {
		return exchange;
	}

	public void setExchange(StockExchange exchange) {
		this.exchange = exchange;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
}
