package builder;

import model.Movie;
import model.User;

import java.time.LocalDate;
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

    public MovieBuilder setDescription(String description) {
        movie.setDescription(description);
        return this;
    }


    public MovieBuilder setUsers(Set<User> users) {
        movie.setUsers(users);
        return this;
    }


    public MovieBuilder setDate(LocalDate date) {
        movie.setDate(date);
        return this;
    }


    public MovieBuilder setRating(Double rating) {
        movie.setRating(rating);
        return this;
    }


    @Override
    public Movie result() throws IllegalAccessException {
        return movie;
    }

}
