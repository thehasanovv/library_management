package com.library.dao;

import com.library.entity.BookEntity;
import com.library.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.library.dao.DBConnection.getConnection;
import static com.library.mapper.BookMapper.buildBookEntity;
import static com.library.mapper.BookMapper.buildPreparedStatement;
import static com.library.model.constants.SqlCommandsConstants.*;

public class BookRepository {
    public void addBook(BookEntity book)  {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            buildPreparedStatement(preparedStatement, book).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BookEntity getBook(int id)  {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return buildBookEntity(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(BookEntity book)  {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL)) {
            buildPreparedStatement(preparedStatement, book);
            preparedStatement.setInt(5, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int id)  {
        try (Connection connection = getConnection(); PreparedStatement statement =
                connection.prepareStatement(DELETE_BOOK_SQL)) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new NotFoundException("Book Not Found!");
        }
    }

    public List<BookEntity> getAllBooks()  {
        List<BookEntity> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                books.add(buildBookEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
