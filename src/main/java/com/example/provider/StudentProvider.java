package com.example.provider;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/student-provider")
public class StudentProvider {

	@Value("${server.port}")
	private String port;

	@GetMapping("/get")
	@HystrixCommand(fallbackMethod = "fallBackShow")
	public String getData() {

		if (true) {
			throw new RuntimeException("Exception");
		}
		return "Hello";
	}

	@GetMapping("/data")
	public Student getStudent() {
		return new Student(1, "Sanjay", "AP");
	}

	@GetMapping("/list")
	public List<Student> getStudents() {
		return Arrays.asList(new Student(2, "SIVA", "SKONDA"), new Student(3, "RAO", "ONGOLE"));
	}

	public String fallBackShow() {
		return "Sever down........... ! Try after some time.. Port: " + port;
	}
}
