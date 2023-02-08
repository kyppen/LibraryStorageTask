import MediaLibrary.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    static Boolean mainMenuRunning = false;
    static Scanner sc = new Scanner(System.in);

    public static void Menu(){
        mainMenuRunning = true;
        BookDB.fromDB();
        System.out.println(Book.listOfBooks.size() + " Books has been added from DB");
        MovieDB.fromDB();
        System.out.println(Movie.listOfMovies.size() +  " Movies has been added from DB");
        GameDB.fromDB();
        System.out.println((Game.listOfGames.size() + " Games has been added from DB"));
        printMenu();
        while (mainMenuRunning){
            String input = sc.nextLine();
            switch(input){
                case "0":
                    System.out.println("Printing main menu");
                    printMenu();
                    break;
                case "1":
                    System.out.println("Book menu selected");
                    BookMenu.bookMenu();
                    break;
                case "2":
                    System.out.println("Movie menu selected");
                    MovieMenu.MovieMenu();
                    break;
                case "3":
                    System.out.println("Game menu selected");
                    GameMenu.gameMenu();
                    break;
                case "4":
                    System.out.println("Search title");
                    generalSearchByTitle(sc.nextLine());
                    break;
                case "5":
                    System.out.println("search author/producer/story writer");
                    generalSearchByPerson(sc.nextLine());
                    break;
                case "6":
                    System.out.println("Search genre");
                    generalSearchByGenre(sc.nextLine());
                    break;
                case "9":
                    System.out.println("Exiting");
                    mainMenuRunning = false;
                default:
                    System.out.println("Invalid input detected");
            }

        }
    }
    public static void printMenu(){
        System.out.println("0: Print main menu");
        System.out.println("1: Book Menu");
        System.out.println("2: Movie Menu");
        System.out.println("3: Game Menu");
        System.out.println("4: General Search in all media for a title");
        System.out.println("5: General search in all media for producer/author/storywritter");
        System.out.println("6: General Search in all media for a spesific genre");
        System.out.println("9: Exit");
    }

    public static void generalSearchByTitle(String titleInput){
        ArrayList<Book> bookList = Book.searchByTitle(titleInput);
        for (Book i : bookList) {
            System.out.println("Book: " + i);
        }
        ArrayList<Movie> movieList = Movie.searchByTitle(titleInput);
        for (Movie i : movieList) {
            System.out.println("Movie: " + i);
        }
        ArrayList<Game> gameList = Game.searchByTitle(titleInput);
        for (Game i : gameList){
            System.out.println("Game: " + i);
        }
    }

    public static void generalSearchByPerson(String personInput){
        ArrayList<Book> bookList = Book.searchByAuthor(personInput);
        for (Book i : bookList) {
            System.out.println("Book: " + i);
        }
        ArrayList<Movie> movieList = Movie.searchByDirector(personInput);
        for (Movie i : movieList) {
            System.out.println("Movie: " + i);
        }
        ArrayList<Game> gameList = Game.searchByStoryWriter(personInput);
            for (Game i : gameList){
                System.out.println("Game: " + i);
            }
        }


    public static void generalSearchByGenre(String genreInput){
        ArrayList<Book> bookList = Book.searchByGenre(genreInput);
        for (Book i : bookList) {
            System.out.println("Book: " + i);
        }
        ArrayList<Movie> movieList = Movie.searchByGenre(genreInput);
        for (Movie i : movieList) {
            System.out.println("Movie: " + i);
        }
        ArrayList<Game> gameList = Game.searchByGenre(genreInput);
        for (Game i : gameList) {
            System.out.println("Game: " + i);
        }
    }

}
