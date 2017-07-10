package com.botscrewtest.dao;

import com.botscrewtest.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao extends AbstractDao<Book> implements GenericDao<Book> {

    public BookDao(String table, String column, Connection connection) {
        super(table, column, connection);
    }

    @Override
    protected Book createEntity(ResultSet resultSet) throws SQLException {
        return new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("author"));
    }
}
