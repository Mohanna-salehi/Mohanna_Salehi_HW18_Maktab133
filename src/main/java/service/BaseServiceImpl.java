package service;

import model.BaseEntity;
import repository.BaseRepositoryImpl;
import repository.MovieRepository;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<ID extends Number,T extends BaseEntity<ID>> implements BaseService<ID,T>{
    private final BaseRepositoryImpl<ID,T> repository;

    public BaseServiceImpl(BaseRepositoryImpl<ID,T> repository) {
        this.repository = repository;
    }

    @Override
    public void save(T entity) throws IllegalAccessException {
     repository.save(entity);
    }

    @Override
    public void update(ID id, T entity) {
        repository.update(id,entity);
    }
    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
