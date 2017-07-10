package com.botscrewtest.controller;

import com.botscrewtest.entity.Book;
import com.botscrewtest.service.BookService;

import java.util.List;

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    public void addNewBook(Book book) {
        bookService.add(book);
    }

    public Book detBookById(int id) {
        return bookService.getById(id);
    }

    public List<Book> getBooksByName(String name) {
        return bookService.getByName(name);
    }

    public boolean deleteBook(int id) {
        return bookService.deleteBook(id);
    }

    public boolean updateBook(int id, Book toUpdate) {
        return bookService.updateBook(id, toUpdate);
    }

}
