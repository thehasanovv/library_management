package com.library.mapper;

import com.library.entity.BookEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.library.model.constants.ExceptionConstants.BOOK_NOT_FOUND;

public class BookMapper {
    public static BookEntity buildBookEntity(ResultSet rs) {
        BookEntity bookEntity = new BookEntity();
        try {
            bookEntity.setId(rs.getInt("id"));
            bookEntity.setTitle(rs.getString("title"));
            bookEntity.setAuthor(rs.getString("author"));
            bookEntity.setPublishedYear(rs.getInt("published_year"));
            bookEntity.setGenre(rs.getString("genre"));
        } catch (SQLException e) {
            throw new RuntimeException(BOOK_NOT_FOUND);
        }
        return bookEntity;
    }

    public static PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, BookEntity book) {
        try {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishedYear());
            preparedStatement.setString(4, book.getGenre());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preparedStatement;
    }
}
