package builder;

import model.Movie;
import model.User;

import java.util.Set;

public class MovieBuilder implements Builder<Movie>{
    private Movie movie;


    @Override
    public MovieBuilder reset() {
        movie = new Movie();
        return this;
    }

    public MovieBuilder setTitle(String title) {
        movie.setTitle(title);
        return this;
    }

    public MovieBuilder setGenre(String genre) {
        movie.setGenre(genre);
        return this;
    }

    public MovieBuilder setDuration(Integer duration) {
        movie.setDuration(duration);
        return this;
    }


    public MovieBuilder setUsers(Set<User> users) {
        movie.setUsers(users);
        return this;
    }
    @Override
    public Movie result() throws IllegalAccessException {

        return movie;
    }

}
