import Domain.Movie;
import Repository.FileMovieRepository;
import Repository.IRepository;
import Service.MovieService;
import UI.Ui;
import Validator.IValidator;
import Validator.MovieValidator;

public class Main
{
    public static void main(String[] args)
    {
        IRepository<Movie,Integer> movieRepository = new FileMovieRepository("movies.txt");
        IValidator<Movie> movieIValidator= new MovieValidator();
        MovieService movieService = new MovieService(movieRepository,movieIValidator);
        Ui ui = new Ui();
        ui.setMovieService(movieService);
        ui.show();
    }
}
