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
import com.smc.model.IpoDetails;
import com.smc.repository.IpoDetailsRepository;

@RestController
@RequestMapping("/ipo")
public class IpoDetailsController {
	
	@Autowired
	IpoDetailsRepository repository;
	
	@GetMapping("/{id}")
	public IpoDetails getIpo(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("IPO Details", "id", id));
	}
	
	@GetMapping("/")
	public List<IpoDetails> getIpo() {
		return repository.findAllByOrderByOpenDateDesc();
	}
	
	@PostMapping("/")
	public IpoDetails createIpo(@Valid @RequestBody IpoDetails ipo) {
		return repository.save(ipo);
	}
	
	@PutMapping("/{id}")
	public IpoDetails updateIpo(@PathVariable Long id, @Valid @RequestBody IpoDetails ipo) {
		return repository.findById(id).map(foundIpo -> {
			updateIpo(foundIpo, ipo);
			return repository.save(foundIpo);
		}).orElseThrow(() -> new ResourceNotFoundException("IPO Details", "id", id));
	}

	private void updateIpo(IpoDetails foundIpo, @Valid IpoDetails ipo) {
		foundIpo.setOpenDate(ipo.getOpenDate());
		foundIpo.setPrice(ipo.getPrice());
		foundIpo.setRemarks(ipo.getRemarks());
		foundIpo.setTotalShares(ipo.getTotalShares());
		
		foundIpo.setCompany(ipo.getCompany());
		foundIpo.setStockExchange(ipo.getStockExchange());
	}

}
