package com.smc.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "UPLOADED_FILES")
public class FileRecord {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="fileName", nullable = false)
	private String fileName;
	
	@Column(name="state", nullable = false)
	private int state; //0=new/processing, 1=processed, 2=error
	
	@Column(name="message")
	private String message; //contains processing status or error message
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="exchange_name")
	private String exchangeName;
	
	@Column(name="records_processed")
	private int recordsProcessed;
	
	@Column(name="total_records")
	private int totalRecords;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fromDate")
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="toDate")
	private Date toDate;
	
	@JsonIgnore
	@Lob
	@Column(name="file_data")
	private Blob fileData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public int getRecordsProcessed() {
		return recordsProcessed;
	}

	public void setRecordsProcessed(int recordsProcessed) {
		this.recordsProcessed = recordsProcessed;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Blob getFileData() {
		return fileData;
	}

	public void setFileData(Blob fileData) {
		this.fileData = fileData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
