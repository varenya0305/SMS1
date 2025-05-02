package com.iorta.sboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iorta.sboot.dao.SMS1DAO;
import com.iorta.sboot.dto.SMS1DTO;
import com.iorta.sboot.repository.SMS1Repository;
import com.iorta.sboot.service.SMS1Service;

@Service
public class SMS1ServiceImpl implements SMS1Service{
	
	@Autowired
	private SMS1Repository studentRepository;
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public String saveStudent(SMS1DTO studentDTO) {
		SMS1DAO student = new SMS1DAO();
		student.setName(studentDTO.getName());
		student.setEmail(studentDTO.getEmail());
		student.setCourse(studentDTO.getCourse());

		studentRepository.save(student);		
		return "Student saved successfully";
	}

	@Override
	public List<SMS1DTO> getAllStudents() {
		return studentRepository.findAll()
				.stream()
				.map(student -> mapper.map(student, SMS1DTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public String updateStudent(Long id, SMS1DTO studentDTO) {
		return studentRepository.findById(id).map(student -> {
			student.setName(studentDTO.getName());
			student.setEmail(studentDTO.getEmail());
			student.setCourse(studentDTO.getCourse());
			studentRepository.save(student);
			return "Student updated successfully";
		}).orElse("Student not found");
	}

	@Override
	public String deleteStudent(Long id) {
		return studentRepository.findById(id).map(student -> {
			studentRepository.deleteById(id);
			return "Student deleted successfully";
		}).orElse("Student not found");
	}

	@Override
	public List<SMS1DTO> searchByName(String name) {
		List<SMS1DAO> students = studentRepository.findByNameContainingIgnoreCase(name);
		return students.stream()
				.map(student -> mapper.map(student, SMS1DTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public SMS1DTO searchByEmail(String email) {
		SMS1DAO student = studentRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Student not found with email: " + email));
		return mapper.map(student, SMS1DTO.class);
	}

	@Override
	public Page<SMS1DTO> getPaginatedStudents(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<SMS1DAO> studentPage = studentRepository.findAll(pageable);
		
		return studentPage.map(student -> mapper.map(student, SMS1DTO.class));
	}
}
