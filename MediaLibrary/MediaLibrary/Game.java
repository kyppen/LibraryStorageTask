package MediaLibrary;

import java.util.ArrayList;

public class Game extends Media{

    public static ArrayList<Game> listOfGames = new ArrayList();
    private String storywriter;
    private String publishingcompany;

    public Game(String title, String storywriter, String publishingcompany, String genre){
        this.setTitle(title);
        this.storywriter = storywriter;
        this.publishingcompany = publishingcompany;
        this.setGenre(genre);
    }
    public void setStorywriter(String storywriter){
        this.storywriter = storywriter;
    }
    public String getStorywriter(){
        return storywriter;
    }
    public void setPublishingcompany(String publishingcompany){
        this.publishingcompany = publishingcompany;
    }
    public String getPublishingcompany(){
        return publishingcompany;
    }

    public String toString() {
        return getId() + " |||  Title: " + getTitle() + " ||| Story writer: " + getStorywriter() + " || Publishing company: " + getPublishingcompany() + " || Genre: " + getGenre();
    }

    public static void printAll() {
        for (Game i : listOfGames) {
            System.out.println(i);
        }
    }
    public static void addGamesToList(String id, String title, String storywriter, String publishingcompany, String genre){
        listOfGames.add(new Game(title, storywriter, publishingcompany, genre));
        listOfGames.get(listOfGames.size() - 1).setId(id);
    }

    public static ArrayList searchByGenre(String inputgenre){
        ArrayList<Game> gameGenreList = new ArrayList<>();
        for (Game i : listOfGames) {
            if (i.getGenre().toLowerCase().contains(inputgenre.toLowerCase())){
                gameGenreList.add(i);
            }
        }
        return gameGenreList;
    }
    public static ArrayList searchByStoryWriter(String inputstorywriter){
        ArrayList<Game> gameStorywriterList = new ArrayList<>();
        for (Game i : listOfGames) {
            if (i.getStorywriter().toLowerCase().contains(inputstorywriter.toLowerCase())){
                gameStorywriterList.add(i);
            }
        }
        return gameStorywriterList;
    }
    public static ArrayList searchByTitle(String inputtitle){
        ArrayList<Game> gameTitleList = new ArrayList<>();
        for (Game i : listOfGames) {
            if(i.getTitle().toLowerCase().contains(inputtitle.toLowerCase())){
                gameTitleList.add(i);
            }
        }
        return gameTitleList;
    }
}
