package Service;

import Domain.Movie;
import Repository.IRepository;
import Validator.IValidator;

import java.util.List;

public class MovieService
{
    private IRepository<Movie, Integer> movieIRepository;
    private IValidator<Movie> movieIValidator;

    public MovieService(IRepository<Movie, Integer> movieIRepository, IValidator<Movie> movieIValidator)
    {
        this.movieIRepository = movieIRepository;
        this.movieIValidator = movieIValidator;
    }

    public boolean addMovie(String title, String director, int year, List<String> actors, String category, List<String> keywords) throws Exception
    {
        Movie movie = new Movie(0,title,director,year,actors,category,keywords);
        movieIValidator.validate(movie);
        return movieIRepository.add(movie);
    }

    public boolean remove(Integer id)
    {
        return movieIRepository.remove(id);
    }

    public boolean update(Integer id,Movie movie) throws Exception
    {
        movieIValidator.validate(movie);
        return movieIRepository.update(id,movie);
    }

    public Movie getMovie(Integer id)
    {
        return movieIRepository.find(id);
    }
    public List<Movie> getMovies()
    {
        return movieIRepository.getAll();
    }
}
