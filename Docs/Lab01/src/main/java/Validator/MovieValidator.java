package Validator;

import Domain.Movie;

public class MovieValidator implements IValidator<Movie>
{
    @Override
    public void validate(Movie movie) throws Exception
    {
        //System.out.println(movie);
        String err="";
        if(movie.getDirector()==null||movie.getDirector().length()==0||movie.getDirector().length()>255)
            err+="Invalid director name!\n";
        if(!(movie.getYear()>1900&&movie.getYear()<2100))
            err+="Invalid year!\n";
        if (movie.getTitle()==null||movie.getTitle().length()==0||movie.getTitle().length()>255)
            err+="Invalid title!\n";
        if(movie.getCategory()==null||movie.getCategory().length()==0)
            err+="Invalid category!\n";
        if(movie.getActors()!=null&&movie.getActors().size()>0)
        {
            for(String act :movie.getActors())
                if(act==null||act.length()==0)
                {
                    err += "One or more actors name are invalid!\n";
                    break;
                }
        }
        else err+="Invalid actors name!\n";

        if(movie.getKeywords()!=null&&movie.getKeywords().size()>0)
        {
            for(String key:movie.getKeywords())
                if(key==null||key.length()==0)
                    err+="One or more keys are inalid!\n";

        }
        else err+="Invalid keywords!\n";
        //System.out.println("err"+err);
        if(err.compareTo("")!=0)
            throw new Exception(err);
    }
}
