package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.BaseEntity;
import util.Connection;

import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<ID extends Number,T  extends BaseEntity<ID>> implements BaseRepository<ID,T>{
    private final Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        EntityManager em = Connection.getConnection();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("saving is failed"+e.getMessage());
        }finally {
            em.close();
        }
    }
    @Override
    public void update(ID id, T entity) {
        EntityManager em = Connection.getConnection();
        try{
            em.getTransaction().begin();
            entity.setId(id);
            em.merge(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("updating is failed"+e.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = Connection.getConnection();
        try{
            em.getTransaction().begin();
            T found = em.find(entityClass, id);
            em.getTransaction().commit();
            return Optional.ofNullable(found);
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("data with the id is not found"+e.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager em = Connection.getConnection();
        try{
            em.getTransaction().begin();
            TypedQuery<T> queryByBrand = em.createQuery("select * from "+entityClass.getName()+" o",entityClass);
            em.getTransaction().commit();
            return queryByBrand.getResultList();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("there is no data: "+e.getMessage());
        }finally {
            em.close();
        }
    }
}
