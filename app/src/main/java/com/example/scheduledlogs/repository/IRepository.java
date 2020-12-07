package com.example.scheduledlogs.repository;

import com.example.scheduledlogs.model.RandomName;

import java.util.List;

public interface IRepository<T> {
    T get(int id);
    List<T> getList();
    void insert(T t);
    void delete(T t);
    void update(T t);
}
