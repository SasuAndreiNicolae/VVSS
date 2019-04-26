import Domain.Movie;
import Repository.FileMovieRepository;
import Repository.IRepository;
import Service.MovieService;
import UI.Ui;
import Validator.IValidator;
import Validator.MovieValidator;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        IRepository<Movie, Integer> movieRepository = new FileMovieRepository("movies.txt");
        //IRepository<Movie,Integer> movieRepository = new FileMovieRepository("movies1.txt");
        IValidator<Movie> movieIValidator = new MovieValidator();
        MovieService movieService = new MovieService(movieRepository, movieIValidator);
        Ui ui = new Ui();
        ui.setMovieService(movieService);
        ui.show();
    }
}
