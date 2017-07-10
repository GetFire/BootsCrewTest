package com.botscrewtest.dao;

import java.util.List;

public interface GenericDao<T> {

    void add(T toAdd);

    List<T> getAll();

    T getById(int id);

    List<T> getByName(String name);

    boolean deleteById(int id);

    boolean updateById(int id, T toUpdate);


}
