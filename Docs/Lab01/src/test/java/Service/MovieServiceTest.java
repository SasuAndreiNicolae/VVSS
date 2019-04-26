package Service;

import Domain.Movie;
import Repository.FileMovieRepository;
import Repository.IRepository;
import Validator.MovieValidator;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MovieServiceTest
{

    private MovieValidator movieValidator;
    private FileMovieRepository fileMovieRepository;
    private MovieService movieService;
    String title255="123456789012345678902134567890123456789012345678901234567890123456789012345678901234567890098765432112345678901234567890213456789012345678901234567890123456789012345678901234567890123456789009876543218787878787878787878787878787878787878787654321234567890";
    String title254="12345678901234567890213456789012345678901234567890123456789012345678901234567890123456789009876543211234567890123456789021345678901234567890123456789012345678901234567890123456789012345678900987654321878787878787878787878787878787878787878765432123456789";
    String title256="1234567890123456789021345678901234567890123456789012345678901234567890123456789012345678900987654321123456789012345678902134567890123456789012345678901234567890123456789012345678901234567890098765432187878787878787878787878787878787878787876543212345678909";

    List<String> actors;
    List<String> keywords;

    @Before
    public void setUp() throws Exception
    {
        fileMovieRepository= new FileMovieRepository("moviesTest.txt");
        movieValidator = new MovieValidator();
        movieService = new MovieService(fileMovieRepository,movieValidator);
         actors = new LinkedList<>();
        actors.add("A1");
        actors.add("A2");
        actors.add("A3");
         keywords= new LinkedList<>();
        keywords.add("K1");
        keywords.add("K2");
        keywords.add("K3");
//        1;m1;Catalin;1999;a;a;a
        movieService.addMovie("m1","Catalin",1996, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("a1","Catalin",1999, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m2","Madalina",1989, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m3","Andrei",1979, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m4","Mihai",1969, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m5","Ionatan",1990, Arrays.asList("a"),"A",Arrays.asList("a"));

//        2;m2;Madalina;1999;a;a;a
//        3;m3;Georgiana;1999;a;1;a
//        4;m4;Andrei;1999;a;1;a
//        5;m5;Mihai;1999;a;a;a
//        6;m6;Ionatan;1999;a;a;a
//        6;m6;;1999;a;a;a
    }

    @After
    public void tearDown() throws Exception
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("moviesTest.txt")))
        {
            writer.write("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void addMovie1Invalid()
    {
        try//1
        {
            movieService.addMovie("","d1",2000,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }
    @Test
    public void addMovie2Valid()
    {
        try//2
        {
            movieService.addMovie("M","d1",2000,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    @Test
    public void addMovie3Valid()
    {
        try//3
        {
            movieService.addMovie(title255,"d1",2000,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    @Test
    public void addMovie4Valid()
    {
        try//4
        {
            movieService.addMovie(title254,"d13",2004,actors,"c9",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    @Test
    public void addMovie5Invalid()
    {
        try//5
        {
            movieService.addMovie(title256,"d1",2000,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }
    @Test
    public void addMovie6Invalid()
    {
        //Director
        try//6
        {
            movieService.addMovie("T","",2000,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }
    @Test
    public void addMovie7Valid()
    {
        try//7
        {
            movieService.addMovie("T","D",2010,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    @Test
    public void addMovie8Valid()
    {
        try//8
        {
            movieService.addMovie("Tt",title255,2000,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    @Test
    public void addMovie9Valid()
    {
        try//9
        {
            movieService.addMovie("tt",title254,2000,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    @Test
    public void addMovie10Invalid()
    {
        try//10
        {
            movieService.addMovie("t",title256,2000,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    @Test
    public void getMoviesByDirectorValid()
    {
        fileMovieRepository.add(new Movie(0,"m6","",1999, Arrays.asList("a"),"A",Arrays.asList("a")));
        List<String> directors= new ArrayList<>();
        for (Movie m: movieService.getMoviesByDirector("in"))
            directors.add(m.getDirector());

        assert directors.contains("Catalin");
        assert directors.contains("Madalina");

        assert movieService.getMoviesByDirector("en").size()==0;

        IRepository<Movie,Integer> movieRepo = new FileMovieRepository("movies1.txt");
        MovieService movieService1 = new MovieService(movieRepo,movieValidator);
        assert  movieService1.getMoviesByDirector("a").size()==0;
    }
    @Test
    public void getMoviesByDirectorInvalid()
    {
        assert movieService.getMoviesByDirector(null).size()==0;
    }


    @Test
    public void getMovieTitlesByDirector1Invalid()
    {
        List<Pair<String,Integer>> titles = movieService.getMovieTitleByDirector("");
        assert titles!=null;
        assert titles.size()==0;
    }

    @Test
    public void getMovieTitlesByDirector2Valid()
    {
        List<Pair<String,Integer>> titles = movieService.getMovieTitleByDirector("Catalin");
        assert titles!=null;
        assert titles.size()>0;
        assert titles.indexOf(new Pair<>("m1",1996))>=0;
        assert titles.indexOf(new Pair<>("a1",1999))>=0;

        for (int i=0;i<titles.size();i++)
        {
            for (int j=i+1;j<titles.size();j++)
            {
                assert titles.get(j).getValue()>titles.get(i).getValue();
            }
        }
    }
}