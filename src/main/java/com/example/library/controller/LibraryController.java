package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * Аннотация @RestController указывает, что этот класс принимает HTTP-запросы
 * и возвращает данные, автоматически преобразуемые в JSON.
 */
@RestController
/*
 * Аннотация @RequestMapping("library") определяет базовый URL для всех методов внутри контроллера,
 * например, все эндпоинты будут начинаться с /library.
 */
@RequestMapping("/library")
public class LibraryController {

    /*
     * Здесь мы создаём временную "базу данных" – список книг,
     * который хранится в памяти. Таким образом, мы избавляемся от необходимости подключения к БД.
     */
    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1, "Effective Java", "Joshua Bloch"),
            new Book(2, "Clean Code", "Robert C. Martin"),
            new Book(3, "Java Concurrency in Practice", "Brian Goetz")
    );

    /**
     * GET эндпоинт с Query параметром.
     * При запросе /library/books?author=Автор будет произведена фильтрация списка книг по имени автора.
     *
     * @param author (опционально) имя автора, по которому отбираются книги.
     * @return список книг в формате JSON.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "author", required = false) String author) {
        List<Book> result = BOOKS;
        // Если параметр author не пустой, выполняем фильтрацию списка
        if (author != null && !author.isEmpty()) {
            result = BOOKS.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.toList());
        }
        // Возвращаем список книг с HTTP-статусом 200 (OK)
        return ResponseEntity.ok(result);
    }

    /**
     * GET эндпоинт с Path параметром.
     * При запросе /library/book/{id} возвращается книга по её идентификатору.
     *
     * @param id идентификатор книги.
     * @return книгу, если она найдена, или HTTP-статус 404 (Not Found), если нет.
     */
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
