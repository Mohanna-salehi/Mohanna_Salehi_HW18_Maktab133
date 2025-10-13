package service;

import model.Movie;
import repository.BaseRepositoryImpl;
import repository.MovieRepository;

public class MovieService extends BaseServiceImpl<Long, Movie> {

    private final MovieRepository movieRepository = new MovieRepository();

    public MovieService() {
        super( new MovieRepository());
    }
    public void delete(Long id) {
        movieRepository.delete(id);
    }
}
