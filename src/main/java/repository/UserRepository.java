package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.User;
import util.Connection;

public class UserRepository extends BaseRepositoryImpl<Long,User>{
    public UserRepository() {
        super(User.class);
    }
    public User findByUsername(String username){
        EntityManager em = Connection.getConnection();
        TypedQuery<User> user =em.createQuery("select a from User a where a.username=: name",User.class).setParameter("name",username);

        if (user.getResultList().isEmpty()){
            return null;
        }
        return user.getResultList().get(0);
    }
}
