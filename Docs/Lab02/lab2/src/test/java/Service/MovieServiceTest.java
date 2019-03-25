package Service;

import Repository.FileMovieRepository;
import Validator.MovieValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;


public class MovieServiceTest
{

    private MovieValidator movieValidator;
    private FileMovieRepository fileMovieRepository;
    private MovieService movieService;
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
    public void addMovie()
    {
        String title255="123456789012345678902134567890123456789012345678901234567890123456789012345678901234567890098765432112345678901234567890213456789012345678901234567890123456789012345678901234567890123456789009876543218787878787878787878787878787878787878787654321234567890";
        String title254="12345678901234567890213456789012345678901234567890123456789012345678901234567890123456789009876543211234567890123456789021345678901234567890123456789012345678901234567890123456789012345678900987654321878787878787878787878787878787878787878765432123456789";
        String title256="1234567890123456789021345678901234567890123456789012345678901234567890123456789012345678900987654321123456789012345678902134567890123456789012345678901234567890123456789012345678901234567890098765432187878787878787878787878787878787878787876543212345678909";

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
}