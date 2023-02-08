package MediaLibrary;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                    addBook();
                    break;
                case "3":
                    System.out.println("Modify a book");
                    modifyBook();
                    break;
                case "4":
                    System.out.println("Enter a Genre: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    ArrayList<Book> resultGenreBooks = Book.searchByGenre(input);
                    for (Book i : resultGenreBooks) {
                        System.out.println(i);
                    }
                    break;
                case "5":
                    System.out.println("Enter an Author: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    ArrayList<Book> resultAuthorBooks = Book.searchByAuthor(input);
                    for (Book i : resultAuthorBooks) {
                        System.out.println(i);
                    }
                    break;
                case "6":
                    System.out.println("Enter an Title: ");
                    input = sc.nextLine();
                    System.out.println("Printing Books that contains: " + input);
                    ArrayList<Book> resultTitleBooks = Book.searchByTitle(input);
                    for (Book i : resultTitleBooks) {
                        System.out.println(i);
                    }
                    break;
                case "7":
                    System.out.println("Remove a Book");
                    removeBook();
                    break;
                case "9":
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


    public static void removeBook(){
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        int userinput;
        for (Book i : Book.listOfBooks) {
            System.out.println(i);
        }
        while (inputAccepted.equals(false)) {
            try {
                int maxvalue = Book.listOfBooks.size();
                userinput = sc.nextInt();
                if (maxvalue >= userinput){
                    userinput = userinput - 1;
                    System.out.println(Book.listOfBooks.get(userinput));
                    System.out.println("This book has now been removed");
                    BookDB.removeBookDB(Book.listOfBooks.get(userinput));
                    Book.listOfBooks.remove(userinput);
                    System.out.println("This Book has now been removed, type 1 to see all the books.");


                    inputAccepted = true;
                }else{
                    maxvalue = Book.listOfBooks.size();
                    System.out.println("input was out of bounds. Max value: " + maxvalue + " || your input: " + userinput );
                }
            } catch (InputMismatchException e) {
                System.out.println("Seems like you inputted something other then an int");
                sc.nextLine();
            }
        }
    }

    public static void addBook(){
        boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title:");
        String title = sc.nextLine();
        System.out.println("Enter author:");
        String author = sc.nextLine();
        System.out.println("Enter pagecount:");
        String pagecount = "12";
        while (!inputAccepted){
            pagecount = sc.nextLine();
            try{
                int pagecountint = Integer.parseInt(pagecount);
                inputAccepted = true;
            }catch(Exception e){
                System.out.println("Pagecount input is invalid, try entering a number");
            }
        }

        System.out.println("Enter genre:");
        String genre = sc.nextLine();
        System.out.println("book has been added");
        BookDB.addBookToDB(title, author, pagecount, genre);

    }

    public static void modifyBook(){
        Boolean modifyingbookcompleted = false;
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);

        int bookselector = 0;
        String bookmodificationselector = "";
        String modfication = "";
        int counter = 0;
        for (Book i : Book.listOfBooks) {
            System.out.println(counter + " ||| " + i);
            counter = counter + 1;

        }
        if (Book.listOfBooks.size() == 0){
            System.out.println("There are no books in storage, and we cant modify something that doesnt exist can we? ;)");
        }else{
            while (inputAccepted.equals(false)) {
                try {
                    bookselector = sc.nextInt();
                    if (Book.listOfBooks.size() > bookselector){
                        inputAccepted = true;
                    }else{
                        int maxvalue = Book.listOfBooks.size();
                        System.out.println("input was out of bounds. Max value: " + maxvalue + " ||| your input: " + bookselector);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Seems like you inputted something other then an int");
                    sc.nextLine();
                }
            }
            System.out.println("You have Selected to modify");
            System.out.println(Book.listOfBooks.get(bookselector));
            while (modifyingbookcompleted.equals(false)) {
                System.out.println("Select which field to modify");
                printModifyOptions();
                while (bookmodificationselector.equals("")) {
                    bookmodificationselector = sc.nextLine();
                }
                switch (bookmodificationselector) {
                    case "0": {
                        System.out.println("Current Title: " + Book.listOfBooks.get(bookselector).getTitle());
                        System.out.println("Enter a new title");
                        modfication = sc.nextLine();
                        Book.listOfBooks.get(bookselector).setTitle(modfication);
                        BookDB.writeBookChangesToDB(Book.listOfBooks.get(bookselector), "title");
                        bookmodificationselector = "";
                        break;
                    }
                    case "1": {
                        System.out.println("Current Author: " + Book.listOfBooks.get(bookselector).getAuthor());
                        System.out.println("Enter a new author");
                        modfication = sc.nextLine();
                        Book.listOfBooks.get(bookselector).setAuthor(modfication);
                        BookDB.writeBookChangesToDB(Book.listOfBooks.get(bookselector), "author");
                        bookmodificationselector = "";
                        break;
                    }
                    case "2": {
                        System.out.println("Current Pagecount: " + Book.listOfBooks.get(bookselector).getPagecount());
                        System.out.println("Enter a new pagecount");
                        modfication = sc.nextLine();
                        Book.listOfBooks.get(bookselector).setPagecount(modfication);
                        BookDB.writeBookChangesToDB(Book.listOfBooks.get(bookselector), "pagecount");
                        bookmodificationselector = "";
                        break;
                    }
                    case "3": {
                        System.out.println("Current Genre: " + Book.listOfBooks.get(bookselector).getGenre());
                        System.out.println("Enter a new genre");
                        modfication = sc.nextLine();
                        Book.listOfBooks.get(bookselector).setGenre(modfication.toUpperCase());
                        BookDB.writeBookChangesToDB(Book.listOfBooks.get(bookselector), "genre");
                        bookmodificationselector = "";
                    }
                        break;
                    case "9":{
                        System.out.println("Exiting modification menu");
                        modifyingbookcompleted = true;
                        break;


                    }
                    default: {
                        bookmodificationselector = "";
                        break;
                    }
                }
            }
        }
    }
    public static void printModifyOptions(){
        System.out.println("0: Edit Title");
        System.out.println("1: Edit Author");
        System.out.println("2: Edit page count");
        System.out.println("3: Edit Genre");
        System.out.println("4: Exit modification");
    }
}
