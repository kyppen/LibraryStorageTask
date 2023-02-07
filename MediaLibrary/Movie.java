import java.util.ArrayList;
import java.util.Scanner;

public class Movie extends Media {
    public static ArrayList<Movie> listOfMovies = new ArrayList<>();
    private String director;
    private String length;

    Movie(String title, String director, String length, String genre) {
        this.setTitle(title);
        this.director = director;
        this.length = length;
        this.setGenre(genre);

    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public String toString() {
        return getId() + " |||  Title: " + getTitle() + " ||| Director: " + getDirector() + " || Length in minutes: " + getLength() + " || Genre: " + getGenre();
    }

    public static void printAll() {
        MovieDB.writeMoviesToDB();
        for (Movie i : listOfMovies) {
            System.out.println(i);
        }
    }
    public static void addMovieToList(String id, String title, String director, String length, String genre){
        Movie.listOfMovies.add(new Movie(title, director, length, genre));
        Movie.listOfMovies.get(Movie.listOfMovies.size() - 1).setId(id);
    }

    public static ArrayList searchByGenre(String inputgenre){
        ArrayList<Movie> movieGenreList = new ArrayList<>();
        for (Movie i : listOfMovies) {
            if (i.getGenre().toLowerCase().contains(inputgenre.toLowerCase())){
                movieGenreList.add(i);
            }
        }
        return movieGenreList;
    }
    public static ArrayList searchByDirector(String inputdirector){
        ArrayList<Movie> movieDirectorList = new ArrayList<>();
        for (Movie i : listOfMovies) {
            if (i.getDirector().toLowerCase().contains(inputdirector.toLowerCase())){
                movieDirectorList.add(i);
            }
        }
        return movieDirectorList;
    }
    public static ArrayList searchByTitle(String inputtitle){
        ArrayList<Movie> movieTitleList = new ArrayList<>();
        for (Movie i : listOfMovies) {
            if (i.getTitle().toLowerCase().contains(inputtitle.toLowerCase())){
                movieTitleList.add(i);
            }
        }
        return movieTitleList;
    }
}
