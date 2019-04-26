package Domain;

import java.util.List;

public class Movie
{
    private Integer id;
    private String title;
    private String director;
    private Integer year;
    private List<String> actors;
    private String category;
    private List<String> keywords;

    public Movie() {
    }

    public Movie(Integer id, String title, String director, Integer year, List<String> actors, String category, List<String> keywords) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.actors = actors;
        this.category = category;
        this.keywords = keywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", actors=" + actors +
                ", category='" + category + '\'' +
                ", keywords=" + keywords +
                '}';
    }

    public void replace(Movie movie)
    {
        this.id=movie.id;
        this.title = movie.title;
        this.director = movie.director;
        this.year = movie.year;
        this.actors = movie.actors;
        this.category = movie.category;
        this.keywords = movie.keywords;
    }
}
