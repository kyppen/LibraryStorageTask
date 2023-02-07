import java.util.ArrayList;

public class Game extends Media{

    public static ArrayList<Game> listOfGames = new ArrayList();

    public Game(String id, String title, String genre){
        this.setTitle(title);
        this.setGenre(genre);

    }
}
