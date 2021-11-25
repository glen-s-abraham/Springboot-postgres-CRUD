package com.example.demo.student;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args->{
			Student man = new Student(1L,"man","man@gmail.com",21);
			Student hat = new Student("hat","hat@gmail.com",21);
			studentRepository.saveAll(List.of(man,hat));
		};
		
		
	}
}
