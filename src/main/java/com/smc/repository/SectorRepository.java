package com.smc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smc.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}
