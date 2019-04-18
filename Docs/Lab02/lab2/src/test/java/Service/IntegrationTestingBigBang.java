package Service;

import Domain.Movie;
import Repository.FileMovieRepository;
import Repository.IRepository;
import Validator.IValidator;
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

public class IntegrationTestingBigBang
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
        movieService.addMovie("m1","Mircea",1996, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("a1","Mircea",1999, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m2","Madalina",1989, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m3","Andrei",1979, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m4","Alin",1969, Arrays.asList("a"),"A",Arrays.asList("a"));
        movieService.addMovie("m5","Ionatan",1990, Arrays.asList("a"),"A",Arrays.asList("a"));
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

    public void testAddMovie(MovieService service)
    {
        addMovie1Invalid(service);
        addMovie2Valid(service);
        addMovie3Valid(service);
        addMovie4Valid(service);
        addMovie5Invalid(service);
        addMovie6Invalid(service);
        addMovie7Valid(service);
        addMovie8Valid(service);
        addMovie9Valid(service);
        addMovie10Invalid(service);
    }

    @Test
    public void addMovieTest()
    {
        testAddMovie(movieService);
    }
    @Test
    public void getMoviesByDirectorValid1()
    {
        fileMovieRepository.add(new Movie(0,"m6","",1989, Arrays.asList("a"),"A",Arrays.asList("a")));
        List<String> directors= new ArrayList<>();
        for (Movie m: movieService.getMoviesByDirector("in"))
            directors.add(m.getDirector());

        assert directors.contains("Alin");
        assert directors.contains("Madalina");
    }

    @Test
    public void getMovieTitlesByDirector2Valid()
    {
        List<Pair<String,Integer>> titles = movieService.getMovieTitleByDirector("Mircea");
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

    @Test
    public void integrateTestBigBang()
    {
        IRepository<Movie, Integer> movieRepository = new FileMovieRepository("moviesTest.txt");

        IValidator<Movie> movieIValidator = new MovieValidator();
        MovieService localMovieService = new MovieService(movieRepository, movieIValidator);
        ////////////////////////A
        testAddMovie(localMovieService);
        ////////////////////////B
        movieRepository.add(new Movie(0,"m6","",1989, Arrays.asList("a"),"A",Arrays.asList("a")));
        List<String> directors= new ArrayList<>();
        for (Movie m: localMovieService.getMoviesByDirector("in"))
            directors.add(m.getDirector());

        assert directors.contains("Alin");
        assert directors.contains("Madalina");
        assert movieService.getMoviesByDirector("en").size()==0;
        assert movieService.getMoviesByDirector(null).size()==0;
        //////////////////////C
        List<Pair<String,Integer>> titles = localMovieService.getMovieTitleByDirector("Mircea");
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
        titles = movieService.getMovieTitleByDirector("");
        assert titles!=null;
        assert titles.size()==0;

    }




    public void addMovie1Invalid(MovieService service)
    {
        try//1
        {
            service.addMovie("","d1",2021,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    public void addMovie2Valid(MovieService service)
    {
        try//2
        {
            service.addMovie("M","d1",2011,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    public void addMovie3Valid(MovieService service)
    {
        try//3
        {
            service.addMovie(title255,"d1",1988,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    public void addMovie4Valid(MovieService service)
    {
        try//4
        {
            service.addMovie(title254,"d13",2025,actors,"c9",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    public void addMovie5Invalid(MovieService service)
    {
        try//5
        {
            service.addMovie(title256,"d1",2018,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    public void addMovie6Invalid(MovieService service)
    {
        //Director
        try//6
        {
            service.addMovie("T","",2023,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    public void addMovie7Valid(MovieService service)
    {
        try//7
        {
            service.addMovie("T","D",2018,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    public void addMovie8Valid(MovieService service)
    {
        try//8
        {
            service.addMovie("Tt",title255,2013,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }
    public void addMovie9Valid(MovieService service)
    {
        try//9
        {
            service.addMovie("tt",title254,2029,actors,"c1",keywords);
            assert true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    public void addMovie10Invalid(MovieService service)
    {
        try//10
        {
            service.addMovie("t",title256,2014,actors,"c1",keywords);
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assert true;
        }
    }
}
