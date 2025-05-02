package com.iorta.sboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iorta.sboot.dto.SMS1DTO;
import com.iorta.sboot.service.SMS1Service;

@RestController
@RequestMapping("/students")
public class SMS1Controller {
	
	@Autowired
	private SMS1Service studentService;
	
	@PostMapping("/saveStudent")
	public String saveStudent(@RequestBody SMS1DTO studentDTO) {
		return studentService.saveStudent(studentDTO);
	}
	
	@GetMapping("/getAllUsers")
	public List<SMS1DTO> getAllUsers() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/searchByName/{name}")
	public List<SMS1DTO> searchByName(@PathVariable String name) {
		return studentService.searchByName(name);
	}
	
	@GetMapping("/searchByEmail/{email}")
	public SMS1DTO searchByEmail(@PathVariable String email) {
		return studentService.searchByEmail(email);
	}
	
	@PutMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable Long id, @RequestBody SMS1DTO studentDTO) {
		return studentService.updateStudent(id, studentDTO);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id) {
		return studentService.deleteStudent(id);
	}
	
	@GetMapping("/paginated")
	public Page<SMS1DTO> getPaginatedStudents(@RequestParam int page, @RequestParam int size) {
		return studentService.getPaginatedStudents(page, size);
	}

}
