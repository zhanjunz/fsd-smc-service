package com.smc.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STOCK_PRICE")
public class StockPrice {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
		
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "exchange_id", nullable = false)
	private StockExchange stockExchange;
	
	@Column(name="price", nullable = false)
	private float price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timestamp", nullable = false)
	private Calendar timestamp;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	
}
