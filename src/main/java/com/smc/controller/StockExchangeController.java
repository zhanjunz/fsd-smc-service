package com.smc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smc.exception.ResourceNotFoundException;
import com.smc.model.StockExchange;
import com.smc.repository.StockExchangeRepository;

@RestController
@RequestMapping("/stock-exchange")
public class StockExchangeController {
	
	@Autowired
	StockExchangeRepository repository;
	
	@GetMapping("/{id}")
	public StockExchange getExchange(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock Exchange", "id", id));
	}
	
	@GetMapping("/")
	public List<StockExchange> getExchange() {
		return repository.findAll();
	}
	
	@PostMapping("/")
	public StockExchange createExchange(@Valid @RequestBody StockExchange exchange) {
		return repository.save(exchange);
	}
	
	@PutMapping("/{id}")
	public StockExchange updateExchange(@PathVariable Long id, @Valid @RequestBody StockExchange exchange) {
		return repository.findById(id).map(foundExchange -> {
			updateExchange(foundExchange, exchange);
			return repository.save(foundExchange);
		}).orElseThrow(() -> new ResourceNotFoundException("Stock Exchange", "id", id));
	}

	private void updateExchange(StockExchange foundExchange, @Valid StockExchange exchange) {
		foundExchange.setAddress(exchange.getAddress());
		foundExchange.setBrief(exchange.getBrief());
		foundExchange.setCode(exchange.getCode());
		foundExchange.setRemarks(exchange.getRemarks());
	}

}
