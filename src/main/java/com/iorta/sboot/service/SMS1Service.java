package com.iorta.sboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.iorta.sboot.dto.SMS1DTO;

public interface SMS1Service {
	String saveStudent(SMS1DTO studentDTO);
	
	List<SMS1DTO> getAllStudents();
	
	String updateStudent(Long id, SMS1DTO studentDTO);
	
	String deleteStudent(Long id);
	
	List<SMS1DTO> searchByName(String name);
	
	SMS1DTO searchByEmail(String email);
	
	Page<SMS1DTO> getPaginatedStudents(int page, int size);

}
