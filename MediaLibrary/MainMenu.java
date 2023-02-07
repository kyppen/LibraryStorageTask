

import java.util.Scanner;

public class MainMenu {

    static Boolean mainMenuRunning = false;
    static Scanner sc = new Scanner(System.in);

    public static void Menu(){
        mainMenuRunning = true;
        BookDB.fromDB();
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
                    System.out.println("unfinished");
                    break;
                case "3":
                    System.out.println("Game menu selected");
                    System.out.println("unfinished");
                    break;
                case "4":
                    System.out.println("Search title");
                    System.out.println("unfinished");
                    break;
                case "5":
                    System.out.println("search author/producer/story writer");
                    System.out.println("unfinished");
                    break;
                case "6":
                    System.out.println("Search genre");
                    System.out.println("unfinished");
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

}
