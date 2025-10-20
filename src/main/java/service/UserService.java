package service;

import builder.UserBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.User;
import repository.BaseRepositoryImpl;
import repository.UserRepository;
import util.Connection;
import util.EncodingPassword;

public class UserService extends BaseServiceImpl<Long, User> {
    private static final UserRepository userRepository = new UserRepository();
    private final UserBuilder userBuilder = new UserBuilder();
    public UserService() {
        super(new UserRepository());
    }

    @Override
    public void save(User user) throws IllegalAccessException {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail()==null || user.getRole() == null){
            throw new RuntimeException("invalid information");
        }
        String username = user.getUsername();
        if(userRepository.findByUsername(username) != null){
            throw new RuntimeException("Invalid username");
        }
        String password = user.getPassword();
        String hashingPassword = EncodingPassword.encode(password);

        user = userBuilder.reset()
                .setName(username)
                .setEmail(user.getEmail()).setRole(user.getRole())
                .setPassword(hashingPassword).result();

        userRepository.save(user);
    }

    public User login(String username, String password) {

        if (username == null || password == null){
            throw new RuntimeException("username or password are not correct");
        }
        User found = userRepository.findByUsername(username);
        if (found ==null){
            throw new RuntimeException("enter information correctly");
        }
        if (!EncodingPassword.verify(password,found.getPassword())){
            throw new RuntimeException("invalid password ");
        }
     return found;
    }

    public User findByUsername(String username){
      return userRepository.findByUsername(username);
    }





}
