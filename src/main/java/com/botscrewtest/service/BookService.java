package com.botscrewtest.service;

import com.botscrewtest.dao.BookDao;
import com.botscrewtest.entity.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public void add(Book book) {
        bookDao.add(book);
    }

    public Book getById(int id) {
        return bookDao.getById(id);
    }

    public List<Book> getByName(String name) {
        return bookDao.getByName(name);
    }

    public boolean deleteBook(int id) {
        return bookDao.deleteById(id);
    }

    public boolean updateBook(int id, Book toUpdate) {
        return bookDao.updateById(id, toUpdate);
    }


}
