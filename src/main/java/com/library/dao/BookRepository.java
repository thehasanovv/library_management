package com.library.dao;

import com.library.entity.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.library.dao.DBConnection.getConnection;
import static com.library.model.SqlCommandsConstants.*;

public class BookRepository {
    public void addBook(BookEntity book) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishedYear());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.executeUpdate();
        }
    }

    public BookEntity getBook(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Book not found with id: " + id);
            }

            BookEntity bookEntity = new BookEntity();
            while (rs.next()) {
                bookEntity.setId(rs.getInt("id"));
                bookEntity.setTitle(rs.getString("title"));
                bookEntity.setAuthor(rs.getString("author"));
                bookEntity.setPublishedYear(rs.getInt("published_year"));
                bookEntity.setGenre(rs.getString("genre"));
            }
            return bookEntity;
        }
    }

    public void updateBook(BookEntity book) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishedYear());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setInt(5, book.getId());

            preparedStatement.executeUpdate();
        }
    }

    public boolean deleteBook(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement =
                connection.prepareStatement(DELETE_BOOK_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<BookEntity> getAllBooks() throws SQLException {
        List<BookEntity> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BookEntity bookEntity = new BookEntity();

                bookEntity.setId(rs.getInt("id"));
                bookEntity.setTitle(rs.getString("title"));
                bookEntity.setAuthor(rs.getString("author"));
                bookEntity.setPublishedYear(rs.getInt("published_year"));
                bookEntity.setGenre(rs.getString("genre"));
                books.add(bookEntity);
            }
        }
        return books;
    }
}
