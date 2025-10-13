package service;

import model.User;
import repository.BaseRepositoryImpl;
import repository.UserRepository;

public class UserService extends BaseServiceImpl<Long, User> {
    public UserService() {
        super(new UserRepository());
    }
}
