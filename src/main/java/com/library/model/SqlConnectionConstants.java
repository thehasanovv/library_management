package com.library.model;

public interface SqlConnectionConstants {
    String JDBC_URL = "jdbc:mysql://localhost:3306/library_db?allowPublicKeyRetrieval=true&useSSL=false";

    String JDBC_USERNAME = "root";

    String JDBC_PASSWORD = "admin";

    String CREATE_BOOKS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS books (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "title VARCHAR(255) NOT NULL, " +
            "author VARCHAR(255) NOT NULL, " +
            "published_year INT NOT NULL, " +
            "genre VARCHAR(100))";
}
