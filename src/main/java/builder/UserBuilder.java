package builder;

import model.Movie;
import model.Role;
import model.User;

import java.util.Set;

public class UserBuilder implements Builder<User> {
    private User user;


    @Override
    public UserBuilder reset() {
        user = new User();
        return this;
    }

    public UserBuilder setName(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;

    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }
    public UserBuilder setRole(Role role) {
        user.setRole(role);
        return this;
    }

    public UserBuilder setMovie(Set<Movie> movies) {
        user.setMovies(movies);
        return this;
    }

    @Override
    public User result() throws IllegalAccessException {
        return user;
    }

}
