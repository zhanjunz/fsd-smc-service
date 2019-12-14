package com.smc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smc.exception.ResourceNotFoundException;
import com.smc.id.ExchangeCompanyId;
import com.smc.model.ExchangeToCompany;
import com.smc.repository.ExchangeToCompanyRepository;

@RestController
@RequestMapping("/exchange-company-map")
public class ExchangeToCompanyController {
	
	@Autowired
	ExchangeToCompanyRepository repository;
	
	@PostMapping("/")
	public ExchangeToCompany createMap(@Valid @RequestBody ExchangeToCompany e2c) {
		return repository.save(e2c);
	}
	
	@PutMapping("/{exId}/{compId}")
	public ExchangeToCompany updateMap(@PathVariable(value="exId") Long exId, 
									   @PathVariable(value="compId") Long compId, 
									   @Valid @RequestBody ExchangeToCompany e2c) {
		ExchangeCompanyId id = new ExchangeCompanyId(exId, compId);
		return repository.findById(id).map(founde2c -> {
			founde2c.setCompanyId(e2c.getCompanyId());
			founde2c.setExchangeId(e2c.getExchangeId());
			return repository.save(founde2c);
		}).orElseThrow(() -> new ResourceNotFoundException("Exchange to Company map", "id", id));
	}

}
