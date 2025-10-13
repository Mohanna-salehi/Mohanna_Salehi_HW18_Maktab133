package service;

import java.util.List;
import java.util.Optional;

public interface BaseService<ID,T> {
    void save(T entity);
    void update(ID id, T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}

