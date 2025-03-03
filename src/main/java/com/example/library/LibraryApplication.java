package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
//http://localhost:8080/library/books
//http://localhost:8080/library/book/1
//http://localhost:8080/library/books?author=Joshua%20Bloch
