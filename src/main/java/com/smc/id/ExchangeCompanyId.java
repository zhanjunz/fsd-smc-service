package com.smc.id;

import java.io.Serializable;

public class ExchangeCompanyId implements Serializable{

	private static final long serialVersionUID = -4516363377969623521L;
	
	private long exchangeId;
	private long companyId;
	
	public ExchangeCompanyId() {
		// default constructor
	}
	
	public ExchangeCompanyId(long exchangeId, long companyId) {
		super();
		this.exchangeId = exchangeId;
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (exchangeId ^ (exchangeId >>> 32));
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExchangeCompanyId other = (ExchangeCompanyId) obj;
		if (exchangeId != other.exchangeId)
			return false;
		if (companyId != other.companyId)
			return false;
		return true;
	}
}
