package Service;

import Domain.Movie;
import Repository.IRepository;
import Validator.IValidator;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public List<Movie> getMoviesByDirector(String director)
    {
        List<Movie> movies = new ArrayList<>();
        if(director==null)
            return movies;
        int i=0;
        while(i<movieIRepository.getAll().size())
        {
            Movie movie =movieIRepository.getAll().get(i);
            if(movie!=null&&movie.getDirector()!=null)
            {
                if(movie.getDirector().contains(director))
                    movies.add(movie);
            }
            i++;
        }
        return movies;
    }

    //generarea unui raport cu titlurile filmelor regizate de un regizor ordonate dupa anul de aparitie.
    public List<Pair<String ,Integer>> getMovieTitleByDirector(String director)
    {
        List<Pair<String,Integer>> titles =  new LinkedList<>();
        if(director==null||director.length()==0)
            return titles;
        for(Movie m : movieIRepository.getAll())
        {
            if(m.getDirector().compareTo(director)==0)
            {
               titles.add(new Pair<>(m.getTitle(),m.getYear()));
            }
        }
        for(int i=0;i<titles.size();i++)
        {
            for (int j=i+1;j<titles.size();j++)
            {
                if(titles.get(i).getValue()>titles.get(j).getValue())
                {
                    Pair<String,Integer> aux = titles.get(i);
                    titles.set(i,titles.get(j));
                    titles.set(j,aux);
                }
            }
        }
        return titles;
    }
}

























