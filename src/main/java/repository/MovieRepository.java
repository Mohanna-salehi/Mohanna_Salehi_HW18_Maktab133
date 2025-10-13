package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.Movie;
import model.User;
import util.Connection;

import java.util.List;

public class MovieRepository extends BaseRepositoryImpl<Long, Movie> {
    BaseRepositoryImpl userRepository = new UserRepository();
    public MovieRepository() {
        super(Movie.class);
    }

    public void delete(Long id) {
        EntityManager em = Connection.getConnection();
        try{
            em.getTransaction().begin();
            Movie movie = em.find(Movie.class,id);
            List<User> users = em.createQuery("select u from User u join u.movies m  where m.id=:id",User.class).setParameter("id",id).getResultList();
            for (User user : users) {
                user.getMovies().remove(movie);
            }
            em.remove(movie);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("removing is failed"+e.getMessage());
        }finally {
            em.close();
        }
    }


}
