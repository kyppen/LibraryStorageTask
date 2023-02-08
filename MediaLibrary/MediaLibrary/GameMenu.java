package MediaLibrary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMenu {
    static boolean gameMenuRunning = false;

    static Scanner sc = new Scanner(System.in);
    public static void gameMenu(){
        gameMenuRunning = true;
        printGameMenu();
        while(gameMenuRunning){
            String input = sc.nextLine();
            switch(input){
                case "1":
                    System.out.println("Printing all Games");
                    Game.printAll();
                    break;
                case "2":
                    System.out.println("Add a Game");
                    addGame();
                    break;
                case "3":
                    System.out.println("Modify a Game");
                    modifyGame();
                    break;
                case "4":
                    System.out.println("Enter a Genre: ");
                    input = sc.nextLine();
                    System.out.println("Printing Games that contains: " + input);
                    ArrayList<Game> resultGenreGames = Game.searchByGenre(input);
                    for (Game i : resultGenreGames) {
                        System.out.println(i);
                    }
                    break;
                case "5":
                    System.out.println("Enter an Storywriter: ");
                    input = sc.nextLine();
                    System.out.println("Printing Games that contains: " + input);
                    ArrayList<Game> resultStorywriterrGames = Game.searchByStoryWriter(input);
                    for (Game i : resultStorywriterrGames) {
                        System.out.println(i);
                    }
                    break;
                case "6":
                    System.out.println("Enter an Title: ");
                    input = sc.nextLine();
                    System.out.println("Printing Games that contains: " + input);
                    ArrayList<Game> resultTitleGames = Game.searchByTitle(input);
                    for (Game i : resultTitleGames) {
                        System.out.println(i);
                    }
                    break;
                case "7":
                    System.out.println("Remove a Game");
                    removeGame();
                    break;
                case "9":
                    System.out.println("Exiting the Game branch, you are now in the main Menu");
                    gameMenuRunning = false;
                    break;
                default:
                    System.out.println("invalid input");
            }

        }

    }

    public static void printGameMenu(){
        System.out.println("Menu:");
        System.out.println("1: Overview over all Games");
        System.out.println("2: Add a Game");
        System.out.println("3: Modify a Game");
        System.out.println("4: Find a Game based on genre");
        System.out.println("5: Find a Game based on Storywriter");
        System.out.println("6: Find a Game based on title");
        System.out.println("7: Remove a Game");
        System.out.println("8: Exit Games");
    }


    public static void removeGame(){
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        int userinput;
        for (Game i : Game.listOfGames) {
            System.out.println(i);
        }
        while (inputAccepted.equals(false)) {
            try {
                int maxvalue = Game.listOfGames.size();
                userinput = sc.nextInt();
                if (maxvalue >= userinput){
                    userinput = userinput - 1;
                    System.out.println(Game.listOfGames.get(userinput));
                    System.out.println("This Game has now been removed");
                    GameDB.removeGameDB(Game.listOfGames.get(userinput));
                    Game.listOfGames.remove(userinput);
                    System.out.println("This Game has now been removed, type 1 to see all the Games.");


                    inputAccepted = true;
                }else{
                    maxvalue = Game.listOfGames.size();
                    System.out.println("input was out of bounds. Max value: " + maxvalue + " || your input: " + userinput );
                }
            } catch (InputMismatchException e) {
                System.out.println("Seems like you inputted something other then an int");
                sc.nextLine();
            }
        }
    }

    public static void addGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title:");
        String title = sc.nextLine();
        System.out.println("Enter story writer:");
        String storywriter = sc.nextLine();
        System.out.println("Enter publishing company:");
        String publishingcompany = sc.nextLine();
        System.out.println("Enter genre:");
        String genre = sc.nextLine();
        System.out.println("Game has been added");
        GameDB.addGameToDB(title, storywriter, publishingcompany, genre);

    }

    public static void modifyGame(){
        Boolean modifyingGamecompleted = false;
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);

        int Gameselector = 0;
        String Gamemodificationselector = "";
        String modfication = "";
        int counter = 0;
        for (Game i : Game.listOfGames) {
            System.out.println(counter + " ||| " + i);
            counter = counter + 1;

        }
        if (Game.listOfGames.size() == 0){
            System.out.println("There are no Games in storage, and we cant modify something that doesnt exist can we? ;)");
        }else{
            while (inputAccepted.equals(false)) {
                try {
                    Gameselector = sc.nextInt();
                    if (Game.listOfGames.size() > Gameselector){
                        inputAccepted = true;
                    }else{
                        int maxvalue = Game.listOfGames.size();
                        System.out.println("input was out of bounds. Max value: " + maxvalue + " ||| your input: " + Gameselector);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Seems like you inputted something other then an int");
                    sc.nextLine();
                }
            }
            System.out.println("You have Selected to modify");
            System.out.println(Game.listOfGames.get(Gameselector));
            while (modifyingGamecompleted.equals(false)) {
                System.out.println("Select which field to modify");
                printModifyOptions();
                while (Gamemodificationselector.equals("")) {
                    Gamemodificationselector = sc.nextLine();
                }
                switch (Gamemodificationselector) {
                    case "0": {
                        System.out.println("Current Title: " + Game.listOfGames.get(Gameselector).getTitle());
                        System.out.println("Enter a new title");
                        modfication = sc.nextLine();
                        Game.listOfGames.get(Gameselector).setTitle(modfication);
                        GameDB.writeGameChangesToDB(Game.listOfGames.get(Gameselector), "title");
                        Gamemodificationselector = "";
                        break;
                    }
                    case "1": {
                        System.out.println("Current Author: " + Game.listOfGames.get(Gameselector).getStorywriter());
                        System.out.println("Enter a new author");
                        modfication = sc.nextLine();
                        Game.listOfGames.get(Gameselector).setStorywriter(modfication);
                        GameDB.writeGameChangesToDB(Game.listOfGames.get(Gameselector), "storywriter");
                        Gamemodificationselector = "";
                        break;
                    }
                    case "2": {
                        System.out.println("Current Pagecount: " + Game.listOfGames.get(Gameselector).getPublishingcompany());
                        System.out.println("Enter a new pagecount");
                        modfication = sc.nextLine();
                        Game.listOfGames.get(Gameselector).setPublishingcompany(modfication);
                        GameDB.writeGameChangesToDB(Game.listOfGames.get(Gameselector), "publishingcompany");
                        Gamemodificationselector = "";
                        break;
                    }
                    case "3": {
                        System.out.println("Current Genre: " + Game.listOfGames.get(Gameselector).getGenre());
                        System.out.println("Enter a new genre");
                        modfication = sc.nextLine();
                        Game.listOfGames.get(Gameselector).setGenre(modfication.toUpperCase());
                        GameDB.writeGameChangesToDB(Game.listOfGames.get(Gameselector), "genre");
                        Gamemodificationselector = "";
                    }
                    break;
                    case "9":{
                        System.out.println("Exiting modification menu");
                        modifyingGamecompleted = true;
                        break;


                    }
                    default: {
                        Gamemodificationselector = "";
                        break;
                    }
                }
            }
        }
    }
    public static void printModifyOptions(){
        System.out.println("0: Edit Title");
        System.out.println("1: Edit Storywriter");
        System.out.println("2: Edit Publishing Company");
        System.out.println("3: Edit Genre");
        System.out.println("4: Exit modification");
    }
}

