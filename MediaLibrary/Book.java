
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Book extends Media {

    public static ArrayList<Book> listOfBooks = new ArrayList();

    String author;
    String pagecount;


    Book(String title, String author, String pagecount, String genre){
        this.setTitle(title);
        this.author = author;
        this.pagecount = pagecount;
        this.setGenre(genre);
    }

    public void setPagecount(String pagecount){
        this.pagecount = pagecount;
    }
    public String getPagecount(){
        return pagecount;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }

    public String toString(){
        return getId() + " |||  Title: " + getTitle() + "|| Author: " + author + "|| Genre: " + getGenre() + " || Page count: " + pagecount;
    }

    public static void printAll(){
        BookDB.writeBooksToDB();
        System.out.println("PrintAll activated");

        for (Book i : listOfBooks) {
            System.out.println(i);
        }
    }

    public static void addToList(String id, String title, String author, String pagecount, String genre) {
        listOfBooks.add(new Book(title, author, pagecount, genre));
        listOfBooks.get(listOfBooks.size() - 1).setId(id);
    }

    public static void removeBook(){
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        int userinput;
        for (Book i : listOfBooks) {
            System.out.println(i);
        }
        while (inputAccepted.equals(false)) {
            try {
                int maxvalue = listOfBooks.size();
                userinput = sc.nextInt();
                if (maxvalue >= userinput){
                    userinput = userinput - 1;
                    System.out.println(listOfBooks.get(userinput));
                    System.out.println("This book has now been removed");
                    BookDB.removeBookDB(listOfBooks.get(userinput));
                    listOfBooks.remove(userinput);
                    System.out.println("This Book has now been removed, type 1 to see all the books.");


                    inputAccepted = true;
                }else{
                    maxvalue = listOfBooks.size();
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
        for (Book i : listOfBooks) {
            System.out.println(i);
        }
        if (listOfBooks.size() == 0){
            System.out.println("There are no books in storage, and we cant modify something that doesnt exist can we? ;)");
        }else{
            while (inputAccepted.equals(false)) {
                try {
                    bookselector = sc.nextInt() - 1;
                    if (listOfBooks.size() > bookselector){
                        inputAccepted = true;
                    }else{
                        int maxvalue = listOfBooks.size() - 1;
                        System.out.println("input was out of bounds. Max value: " + maxvalue + " ||| your input: " + bookselector);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Seems like you inputted something other then an int");
                    sc.nextLine();
                }
            }
            System.out.println("You have Selected to modify");
            System.out.println(listOfBooks.get(bookselector));
            while (modifyingbookcompleted.equals(false)) {
                System.out.println("Select which field to modify");
                printModifyOptions();
                while (bookmodificationselector.equals("")) {
                    bookmodificationselector = sc.nextLine();
                }
                switch (bookmodificationselector) {
                    case "0": {
                        System.out.println("Current Title: " + listOfBooks.get(bookselector).getTitle());
                        System.out.println("Enter a new title");
                        modfication = sc.nextLine();
                        listOfBooks.get(bookselector).setTitle(modfication);
                        BookDB.writeBookChangesToDB(listOfBooks.get(bookselector), "title");
                        bookmodificationselector = "";
                        break;
                    }
                    case "1": {
                        System.out.println("Current Author: " + listOfBooks.get(bookselector).getAuthor());
                        System.out.println("Enter a new author");
                        modfication = sc.nextLine();
                        listOfBooks.get(bookselector).setAuthor(modfication);
                        BookDB.writeBookChangesToDB(listOfBooks.get(bookselector), "author");
                        bookmodificationselector = "";
                        break;
                    }
                    case "2": {
                        System.out.println("Current Pagecount: " + listOfBooks.get(bookselector).getPagecount());
                        System.out.println("Enter a new pagecount");
                        modfication = sc.nextLine();
                        System.out.println("pagecount");
                        listOfBooks.get(bookselector).setPagecount(modfication);
                        BookDB.writeBookChangesToDB(listOfBooks.get(bookselector), "pagecount");
                        bookmodificationselector = "";
                        break;
                    }
                    case "3": {
                        System.out.println("Current Genre: " + listOfBooks.get(bookselector).getGenre());
                        System.out.println("Enter a new genre");
                        modfication = sc.nextLine();
                        System.out.println("genre");
                        listOfBooks.get(bookselector).setGenre(modfication.toUpperCase());
                        BookDB.writeBookChangesToDB(listOfBooks.get(bookselector), "genre");
                        bookmodificationselector = "";
                        break;
                    }
                    default: {
                        System.out.println("default switch statement triggered");
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
    public static void searchByGenre(String inputgenre){
        for (Book i : listOfBooks) {
            if (i.getGenre().toLowerCase().contains(inputgenre.toLowerCase())){
                System.out.println(i);
            }
        }
    }
    public static void searchByAuthor(String inputauthor){
        for (Book i : listOfBooks) {
            if (i.getAuthor().toLowerCase().contains(inputauthor.toLowerCase())){
                System.out.println(i);
            }
        }
    }
    public static void searchByTitle(String inputtitle){
        for (Book i : listOfBooks){
            if (i.getAuthor().toLowerCase().contains(inputtitle.toLowerCase())){
                System.out.println(i);
            }
        }
    }

}



