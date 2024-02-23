package org.example.interfaces;

public interface querydao {
    void findall();

    Object findById(int id);

    void save(Object e);

    void deleteById(int id);
}
