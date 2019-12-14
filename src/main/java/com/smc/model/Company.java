package com.smc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="company_name", nullable = false)
	private String companyName;
	
	@Column(name="ceo", nullable = false)
	private String ceo;
	
	@Column(name="board_of_directors", nullable = false)
	private String boardOfDirectors;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private List<ExchangeToCompany> exchange;
	
	@Column(name="turnover", nullable = false)
	private float turnover;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector_id", nullable = false)
	private Sector sector;
	
	@Lob
	@Column(name="brief_writeup", nullable = false)
	private String briefWriteup;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public List<ExchangeToCompany> getExchange() {
		return exchange;
	}

	public void setExchange(List<ExchangeToCompany> exchanges) {
		this.exchange = exchanges;
	}

	public float getTurnover() {
		return turnover;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getBriefWriteup() {
		return briefWriteup;
	}

	public void setBriefWriteup(String briefWriteup) {
		this.briefWriteup = briefWriteup;
	}
}
