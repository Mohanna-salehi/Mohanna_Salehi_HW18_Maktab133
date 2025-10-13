package repository;

import model.User;

public class UserRepository extends BaseRepositoryImpl<Long,User>{
    public UserRepository() {
        super(User.class);
    }
}
