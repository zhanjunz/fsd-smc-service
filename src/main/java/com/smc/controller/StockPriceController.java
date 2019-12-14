package com.smc.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smc.exception.ResourceNotFoundException;
import com.smc.model.StockPrice;
import com.smc.repository.StockPriceRepository;

@RestController
@RequestMapping("/price")
public class StockPriceController {
	
	@Autowired
	StockPriceRepository repository;
	
	@GetMapping("/{id}")
	public StockPrice getStockPrice(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock Price", "id", id));
	}
	
	@GetMapping("/for-company-date-range/{id}/{exId}/{date1}/{date2}")
	public List<StockPrice> getPriceByRange(@PathVariable(value = "id") long companyId, 
											@PathVariable(value = "exId") long exchangeId,
											@PathVariable(value = "date1") @DateTimeFormat(pattern = "dd.MM.yyyy") Calendar startDate,
											@PathVariable(value = "date2") @DateTimeFormat(pattern = "dd.MM.yyyy") Calendar endDate) {
		return repository.findByCompanyIdEqualsAndStockExchangeIdEqualsAndTimestampBetweenOrderByTimestampAsc(companyId, exchangeId, startDate, endDate);
	
	}
	
	@GetMapping("/getLastPrice/{compId}/{exId}")
	public StockPrice getLastPrice(@PathVariable(value = "compId") long companyId, @PathVariable(value = "exId") long exchangeId) {
		return repository.getLastPrice(companyId, exchangeId);
	}

}
