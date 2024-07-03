package com.library.service;

import com.library.dao.BookRepository;
import com.library.entity.BookEntity;
import com.library.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

import static com.library.util.PrintMessage.printMessage;

public class BookService {
    BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public void addBook(BookEntity book) {
        try {
            bookRepository.addBook(book);
            printMessage("Book added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BookEntity getBook(int id) {
        BookEntity book;
        try {
            book = bookRepository.getBook(id);
        } catch (SQLException e) {
            throw new NotFoundException("Belə kitab tapılmadı");
        }
        return book;
    }

    public void updateBook(BookEntity book) {
        try {
            bookRepository.updateBook(book);
            if (book == null) {
                printMessage("Book not found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int id) {
        try {
            bookRepository.deleteBook(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookEntity> getAllBooks() {
        List<BookEntity> bookEntities;
        try {
            bookEntities = bookRepository.getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookEntities;
    }
}
