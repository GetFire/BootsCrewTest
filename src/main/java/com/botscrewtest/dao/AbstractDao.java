package com.botscrewtest.dao;

import com.botscrewtest.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Book> implements GenericDao<T> {

    protected Connection connection;
    private String table;
    private String column;

    public AbstractDao(String table, String column, Connection connection) {
        this.table = table;
        this.column = column;
        this.connection = connection;
    }

    @Override
    public void add(T toAdd) {
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO " + table + " (name, author) VALUE (?, ?);")) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setString(1, toAdd.getName());
            statement.setString(2, toAdd.getAuthor());
            connection.commit();
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to DB", ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException("Cannot connect to DB", ex);
            }
        }
    }

    @Override
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM " + table;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                T t = createEntity(resultSet);
                result.add(t);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to DB", ex);
        }
        return result;
    }

    @Override
    public T getById(int id) {
        T result = null;
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = ?;")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = createEntity(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to DB", ex);
        }
        return result;
    }

    @Override
    public List<T> getByName(String name) {
        List<T> result = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM " + table + " WHERE name ='" + name + "'")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                T t = createEntity(rs);
                result.add(t);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to DB", ex);
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM " + table + " WHERE " + column + " = ?;")) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to db", ex);
        }
        return result;
    }

    @Override
    public boolean updateById(int id, T toUpdate) {
        boolean result = false;
        try (PreparedStatement statement = connection
                .prepareStatement("UPDATE " + table + " SET name = ?, author = ? WHERE " + column + "=?;")) {
            String name = toUpdate.getName();
            String author = toUpdate.getAuthor();
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot connect to DB", ex);
        }
        return result;
    }

    protected abstract T createEntity(ResultSet resultSet) throws SQLException;
}
