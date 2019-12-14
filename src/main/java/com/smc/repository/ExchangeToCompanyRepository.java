package com.smc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smc.id.ExchangeCompanyId;
import com.smc.model.ExchangeToCompany;

@Repository
public interface ExchangeToCompanyRepository extends JpaRepository<ExchangeToCompany, ExchangeCompanyId> {
	public ExchangeToCompany findByStockCodeEquals(String code);
}
