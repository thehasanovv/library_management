package com.library.controller;

import com.library.entity.BookEntity;
import com.library.service.BookService;

import java.util.List;
import java.util.Scanner;

import static com.library.model.constants.ExceptionConstants.BOOK_LIST_NOT_FOUND;
import static com.library.util.PrintMessage.printMessage;

public class BookController {
    private final BookService bookService;
    private final Scanner scanner;

    public BookController(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Get Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    getBook();
                    break;
                case 6:
                    return;
                default:
                    printMessage("Invalid choice!");
            }
        }
    }

    private void addBook() {
        printMessage("Enter title: ");
        String title = scanner.nextLine();
        printMessage("Enter author: ");
        String author = scanner.nextLine();
        printMessage("Enter published year: ");
        int publishedYear = scanner.nextInt();
        scanner.nextLine();  // consume newline
        printMessage("Enter genre: ");
        String genre = scanner.nextLine();

        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedYear(publishedYear);
        book.setGenre(genre);

        bookService.addBook(book);
    }

    private void listBooks() {
        List<BookEntity> books = bookService.getAllBooks();
        if (books.size() == 0) {
            printMessage(BOOK_LIST_NOT_FOUND);
        }
        for (BookEntity book : books) {
            printMessage(book);
        }
    }

    private void getBook() {
        printMessage("Enter book ID to get book: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        BookEntity book = bookService.getBook(id);

        System.out.println(book);
    }

    private void updateBook() {
        printMessage("Enter book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        BookEntity book = bookService.getBook(id);
        if (book == null) {
            printMessage("Book not found!");
            return;
        }

        printMessage("Enter new title: ");
        String title = scanner.nextLine();
        printMessage("Enter new author: ");
        String author = scanner.nextLine();
        printMessage("Enter new published year: ");
        int publishedYear = scanner.nextInt();
        scanner.nextLine();  // consume newline
        printMessage("Enter new genre: ");
        String genre = scanner.nextLine();

        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedYear(publishedYear);
        book.setGenre(genre);

        bookService.updateBook(book);
    }

    private void deleteBook() {
        printMessage("Enter book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        bookService.deleteBook(id);
    }
}