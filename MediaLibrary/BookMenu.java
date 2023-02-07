
import java.util.Scanner;

public class BookMenu {

    static boolean bookMenuRunning = false;

    static Scanner sc = new Scanner(System.in);
    public static void bookMenu(){
        bookMenuRunning = true;
        printBookMenu();
        while(bookMenuRunning){
            String input = sc.nextLine();
            switch(input){
                case "1":
                    System.out.println("Printing all Books");
                    Book.printAll();
                    break;
                case "2":
                    System.out.println("Add a book");
                    Book.addBook();
                    break;
                case "3":
                    System.out.println("Modify a book");
                    Book.modifyBook();
                    break;
                case "4":
                    System.out.println("Enter a Genre: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    Book.searchByGenre(input);
                    break;
                case "5":
                    System.out.println("Enter an Author: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    Book.searchByAuthor(input);
                    break;
                case "6":
                    System.out.println("Enter an Title: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    Book.searchByTitle(input);
                    break;
                case "7":
                    System.out.println("Remove a Book");
                    Book.removeBook();
                    break;
                case "8":
                    System.out.println("Exiting the Book branch, you are now in the main Menu");
                    bookMenuRunning = false;
                    break;
                default:
                    System.out.println("invalid input");
            }

        }

    }

    public static void printBookMenu(){
        System.out.println("Menu:");
        System.out.println("1: Overview over all Books");
        System.out.println("2: Add a book");
        System.out.println("3: Modify a Book");
        System.out.println("4: Find a book based on genre");
        System.out.println("5: Find a Book based on author");
        System.out.println("6: Find a book based on title");
        System.out.println("7: Remove a Book");
        System.out.println("8: Exit Books");
    }
}
