package com.example.library.model;

public class Book {

    // Поля класса (атрибуты книги)
    private int id;
    private String title;
    private String author;

    // Конструктор с параметрами, который позволяет создать объект Book с конкретными значениями.
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Геттеры и сеттеры для доступа к полям.
    // Они позволяют другим классам получить или изменить значения полей.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
