package com.smc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smc.exception.ResourceNotFoundException;
import com.smc.id.ExchangeCompanyId;
import com.smc.model.Company;
import com.smc.model.ExchangeToCompany;
import com.smc.repository.CompanyRepository;
import com.smc.repository.ExchangeToCompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyRepository repository;
	
	@Autowired
	ExchangeToCompanyRepository e2cRepo;
	
	@GetMapping("/{page}/{size}")
	public Page<Company> getCompany(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return repository.findAllByOrderByCompanyName(PageRequest.of(page, size));
	}
	
	@GetMapping("/{id}")
	public Company getCompany(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}
	
	@GetMapping("/name/{name}")
	public Company getCompanyByName(@PathVariable(value = "name") String name) {
		return repository.findByCompanyName(name).orElseThrow(() -> new ResourceNotFoundException("Company", "name", name));
	}
	
	@GetMapping("/search/{txt}")
	public List<Company> getCompany(@PathVariable(value = "txt") String txt) {
		return repository.findFirst15ByCompanyNameContainsIgnoreCaseOrderByCompanyName(txt);
	}
	
	@Transactional
	@PostMapping("/")
	public Company createCompany(@Valid @RequestBody Company company) {
		removeInvalidExchanges(company);
		Company newCompany = repository.save(company);
		addExchanges(company, newCompany.getId());
		return newCompany;
	}
	
	@Transactional
	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
		return repository.findById(id).map(foundCompany -> {
			removeInvalidExchanges(company);
			addExchanges(company, id);			
			removeExchanges(company, foundCompany);
			updateCompany(foundCompany, company);
			return repository.save(foundCompany);
		}).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}

	private void removeInvalidExchanges(Company company) {
		List<ExchangeToCompany> newList = new ArrayList<>();
		for(ExchangeToCompany e2c : company.getExchange()) {
			if(e2c.getExchangeId()>0 && e2c.getStockCode()!=null && !e2c.getStockCode().trim().isEmpty()) {
				newList.add(e2c);
			}
		}
		
		company.setExchange(newList);
	}
	
	private void addExchanges(@Valid Company company, long id) {
		for(ExchangeToCompany e2c : company.getExchange()) {
			e2c.setCompanyId(id);
			Optional<ExchangeToCompany> founde2c = e2cRepo.findById(new ExchangeCompanyId(e2c.getExchangeId(), e2c.getCompanyId()));
			if(!founde2c.isPresent()) {
				e2cRepo.save(e2c);
			}
		}
	}
	
	private void removeExchanges(@Valid Company company, Company existing) {
		List<ExchangeToCompany> newList = new ArrayList<>();
		for(ExchangeToCompany e2c : existing.getExchange()) {
			boolean delete = true;
			for(ExchangeToCompany newE2C : company.getExchange()) {
				if(e2c.getCompanyId() == newE2C.getCompanyId() && e2c.getExchangeId() == newE2C.getExchangeId()) {
					delete = false;
					break;
				}
			}
			
			if(delete) {
				e2cRepo.delete(e2c);
			} else {
				newList.add(e2c);
			}			
		}
		existing.setExchange(newList);
	}

	private void updateCompany(Company foundCompany, @Valid Company company) {
		foundCompany.setBoardOfDirectors(company.getBoardOfDirectors());
		foundCompany.setBriefWriteup(company.getBriefWriteup());
		foundCompany.setCeo(company.getCeo());
		foundCompany.setCompanyName(company.getCompanyName());
		foundCompany.setSector(company.getSector());
		foundCompany.setTurnover(company.getTurnover());
		
		List<ExchangeToCompany> exchangeList = company.getExchange();
		if(exchangeList!=null && !exchangeList.isEmpty()) { 
			foundCompany.setExchange(company.getExchange());
		}
	}

}
