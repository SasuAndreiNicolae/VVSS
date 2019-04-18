package UI;

import Service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Ui
{
    private BufferedReader reader;
    public Ui()
    {
         reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private MovieService movieService;

    public void setMovieService(MovieService movieService)
    {
        this.movieService = movieService;
    }

    private void showMenu()
    {
        System.out.println("0.Exit!");
        System.out.println("1.Add movie");
        System.out.println("2.View movies");
        System.out.println("3.View movies by director");
        System.out.println("4.View movie titles for director");
    }

    private void addMovie()
    {
        try
        {
            System.out.println("Movie title:");
            String title = reader.readLine();
            System.out.println("Movie director:");
            String director = reader.readLine();
            System.out.println("Movie year:");
            Integer year = Integer.parseInt(reader.readLine());
            System.out.println("Movie actors number");
            Integer nrActors= Integer.parseInt(reader.readLine());
            List<String> actors = new LinkedList<>();
            for(int i=0;i<nrActors;i++)
            {
                System.out.println("Actor "+i);
                String actor= reader.readLine();
                actors.add(actor);
            }
            System.out.println("Category:");
            String category = reader.readLine();
            System.out.println("Keywords number:");
            List<String> keywords = new LinkedList<>();
            Integer nrKeys= Integer.parseInt(reader.readLine());
            for(int i=0;i<nrKeys;i++)
            {
                System.out.println("Keyword:"+i);
                String key = reader.readLine();
                keywords.add(key);
            }
            movieService.addMovie(title,director,year,actors,category,keywords);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private void viewMovies()
    {
        movieService.getMovies().forEach(System.out::println);
    }
    private void viewMoviesByDirector()
    {
        try
        {
            System.out.println("Director name:");
            String director=reader.readLine();
            movieService.getMoviesByDirector(director).forEach(System.out::println);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void viewMovieTitlesForDirector()
    {
        try
        {
            viewMovies();
            System.out.println("Director name:");
            String director=reader.readLine();
            movieService.getMovieTitleByDirector(director).forEach(System.out::println);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void show()
    {
        try
        {
            boolean ok = true;
            String opt;
            while (ok)
            {
                showMenu();
                opt=reader.readLine();
                if(opt.trim().compareTo("0")==0)
                    ok=false;
                if(opt.trim().compareTo("1")==0)
                    addMovie();
                if(opt.trim().compareTo("2")==0)
                    viewMovies();
                if(opt.trim().compareTo("3")==0)
                    viewMoviesByDirector();
                if(opt.trim().compareTo("4")==0)
                    viewMovieTitlesForDirector();

            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
}
