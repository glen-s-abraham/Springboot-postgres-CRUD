package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	private boolean isEmailTaken(String email) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
		return studentByEmail.isPresent();
	}
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		if(isEmailTaken(student.getEmail()))
			throw new IllegalStateException("email taken");
		studentRepository.save(student);
		
	}

	public void deleteStudent(Long id) {
		if(!studentRepository.existsById(id))
			throw new IllegalStateException("Student with id "+ id + " does not exist");
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		
		Student student=studentRepository.findById(id).orElseThrow(()->new IllegalStateException("Student does not exist"));
		if(name !=null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			if(isEmailTaken(email))
				throw new IllegalStateException("email taken");
			student.setEmail(email);
		}
		
	}
}
