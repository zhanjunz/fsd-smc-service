package com.smc.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.smc.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
	
	//Page<StockPrice> findByCompanyIdAndTimestampAfterAndTimestampBeforeOrderByTimestampDesc(long id, Calendar t1, Calendar t2, PageRequest page);

	List<StockPrice> findByCompanyIdEqualsAndStockExchangeIdEqualsAndTimestampBetweenOrderByTimestampAsc(
			long companyId, long exchangeId, Calendar startDate, Calendar endDate);
	
	@Query("SELECT p FROM StockPrice p where p.company.id=?1 and p.stockExchange.id=?2  and p.timestamp = (select max(x.timestamp) from StockPrice x where x.company.id=?1 and x.stockExchange.id=?2)")
	StockPrice getLastPrice(long companyId, long exchangeId);
}
