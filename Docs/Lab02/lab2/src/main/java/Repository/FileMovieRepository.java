package Repository;

import Domain.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class FileMovieRepository implements IRepository<Movie,Integer>
{
    public FileMovieRepository(String movieFile)
    {
        movies= new LinkedList<>();
        this.movieFile=movieFile;
        readFromFile();
    }

    @Override
    public boolean add(Movie movie)
    {
        if(movie==null)
            return false;
        if(find(movie.getId())!=null)
            return false;
        movie.setId(++maxId);
        movies.add(movie);
        return appendToFile(movie);

    }

    @Override
    public boolean remove(Integer integer)
    {
        for (int i=0;i<movies.size();i++)
        {
            if(movies.get(i).getId()==integer)
            {
                movies.remove(i);
                return writeToFile();
            }
        }
        return false;
    }

    @Override
    public boolean update(Integer integer, Movie movie)
    {
        for (int i=0;i<movies.size();i++)
        {
            if (movies.get(i).getId() == integer)
            {
                movies.get(i).replace(movie);
                return writeToFile();
            }
        }
        return false;
    }


    @Override
    public Movie find(Integer integer)
    {
        for(Movie movie:movies)
            if(movie.getId()==integer)
                return movie;
        return null;
    }

    @Override
    public List<Movie> getAll() {
        return movies;
    }

    private void readFromFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(movieFile)))
        {
            String line;
            while ((line=reader.readLine())!=null)
            {
                String[] pieces= line.split(";");
                Integer id=Integer.parseInt(pieces[0]);
                String title=pieces[1];
                String director=pieces[2];
                Integer year=Integer.parseInt(pieces[3]);
                List<String> actors= new LinkedList<>();
                String category=pieces[5];
                List<String> keywords= new LinkedList<>();
                String[] act=pieces[4].split(",");
                for(String a :act)
                    actors.add(a);
                String[] keys= pieces[6].split(",");
                for(String key:keys)
                    keywords.add(key);
                movies.add(new Movie(id,title,director,year,actors,category,keywords));
                if(id>maxId)
                    maxId=id;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private boolean writeToFile()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(movieFile)))
        {
            for(Movie movie:movies)
            {
                String line= String.valueOf(movie.getId());
                line+=";"+movie.getTitle();
                line+=";"+movie.getDirector();
                line+=";"+movie.getYear();
                line+=";";
                if(movie.getActors().size()>0)
                {
                    for (int i = 0; i < movie.getActors().size() - 1; i++)
                        line += movie.getActors().get(i) + ",";
                    line+=movie.getActors().get(movie.getActors().size()-1)+";";
                }
                line+=movie.getCategory()+";";
                if(movie.getKeywords().size()>0)
                {
                    for (int i = 0; i < movie.getKeywords().size() - 1; i++)
                        line += movie.getKeywords().get(i) + ",";
                    line+=movie.getKeywords().get(movie.getKeywords().size()-1);
                }
                writer.write(line);
                writer.newLine();
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private boolean appendToFile(Movie movie)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(movieFile,true)))
        {
            String line= String.valueOf(movie.getId());
            line+=";"+movie.getTitle();
            line+=";"+movie.getDirector();
            line+=";"+movie.getYear();
            line+=";";
            if(movie.getActors().size()>0)
            {
                for (int i = 0; i < movie.getActors().size() - 1; i++)
                    line += movie.getActors().get(i) + ",";
                line+=movie.getActors().get(movie.getActors().size()-1)+";";
            }
            line+=movie.getCategory()+";";
            if(movie.getKeywords().size()>0)
            {
                for (int i = 0; i < movie.getKeywords().size() - 1; i++)
                    line += movie.getKeywords().get(i) + ",";
                line+=movie.getKeywords().get(movie.getKeywords().size()-1);
            }
            writer.write(line);
            writer.newLine();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<Movie> movies;
    private String movieFile;
    private Integer maxId=0;
}
