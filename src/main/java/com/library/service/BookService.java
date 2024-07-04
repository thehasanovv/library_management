package com.library.service;

import com.library.dao.BookRepository;
import com.library.entity.BookEntity;

import java.util.List;

import static com.library.util.PrintMessage.printMessage;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookEntity book) {
        bookRepository.addBook(book);
        printMessage("Book added successfully");
    }

    public BookEntity getBook(int id) {
        BookEntity book;
        book = bookRepository.getBook(id);
        return book;
    }

    public void updateBook(BookEntity book) {
        bookRepository.updateBook(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
        printMessage("Book deleted successfully");
    }

    public List<BookEntity> getAllBooks() {
        List<BookEntity> bookEntities;
        bookEntities = bookRepository.getAllBooks();
        return bookEntities;
    }
}
