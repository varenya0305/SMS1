package com.iorta.sboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iorta.sboot.dao.SMS1DAO;

public interface SMS1Repository extends JpaRepository<SMS1DAO, Long>{
	List<SMS1DAO> findByNameContainingIgnoreCase(String name);
	
	Optional<SMS1DAO> findByEmail(String email);
}
