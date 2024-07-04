package com.library;

import com.library.controller.BookController;
import com.library.dao.BookRepository;
import com.library.service.BookService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BookController bookController =
                new BookController(new BookService(new BookRepository()), new Scanner(System.in));

        bookController.start();
    }
}