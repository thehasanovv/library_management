package com.library.model.constants;

public interface SqlCommandsConstants {
    String INSERT_BOOK_SQL = "INSERT INTO books (title, author, published_year, genre) VALUES (?, ?, ?, ?)";
    String SELECT_ALL_BOOKS = "SELECT * FROM books";
    String SELECT_BOOK_BY_ID = "SELECT id, title, author, published_year, genre FROM books WHERE id = ?";
    String DELETE_BOOK_SQL = "DELETE FROM books WHERE id = ?";
    String UPDATE_BOOK_SQL = "UPDATE books SET title = ?, author = ?, published_year = ?, genre = ? WHERE id = ?";


}
