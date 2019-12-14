package com.smc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smc.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Page<Company> findAllByOrderByCompanyName(Pageable pageRequest);
	List<Company> findFirst15ByCompanyNameContainsIgnoreCaseOrderByCompanyName(String search);
	Optional<Company> findByCompanyName(String name);

}
