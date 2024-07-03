package com.library;

import com.library.controller.BookController;

import static com.library.dao.CreateBookTable.createBooksTable;

public class Main {
    public static void main(String[] args) {
        createBooksTable();

        BookController bookController = new BookController();
        bookController.start();
    }
}