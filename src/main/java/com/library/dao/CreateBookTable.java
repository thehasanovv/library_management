package com.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.library.dao.DBConnection.getConnection;
import static com.library.model.constants.SqlConnectionConstants.CREATE_BOOKS_TABLE_SQL;

public class CreateBookTable {
    public static void createBooksTable()  {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(CREATE_BOOKS_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
