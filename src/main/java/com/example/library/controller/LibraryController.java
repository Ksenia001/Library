package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1, "Effective Java", "Joshua Bloch"),
            new Book(2, "Clean Code", "Robert C. Martin"),
            new Book(3, "Java Concurrency in Practice", "Brian Goetz")
    );

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "author", required = false) String author) {
        List<Book> result = BOOKS;
        // Если параметр author не пустой, выполняем фильтрацию списка
        if (author != null && !author.isEmpty()) {
            result = BOOKS.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .collect(Stream.toList());
        }
        // Возвращаем список книг с HTTP-статусом 200 (OK)
        return ResponseEntity.ok(result);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Optional<Book> book = BOOKS.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        // Если книга найдена – возвращаем её, иначе – статус 404
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
