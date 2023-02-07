
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

    public static void addBookToList(String id, String title, String author, String pagecount, String genre) {
        listOfBooks.add(new Book(title, author, pagecount, genre));
        listOfBooks.get(listOfBooks.size() - 1).setId(id);
    }

    public static ArrayList searchByGenre(String inputgenre){
        ArrayList<Book> bookGenreList = new ArrayList<>();
        for (Book i : listOfBooks) {
            if (i.getGenre().toLowerCase().contains(inputgenre.toLowerCase())){
                bookGenreList.add(i);
            }
        }
        return bookGenreList;
    }
    public static ArrayList searchByAuthor(String inputauthor){
        ArrayList<Book> bookAuthorList = new ArrayList<>();
        for (Book i : listOfBooks) {
            if (i.getAuthor().toLowerCase().contains(inputauthor.toLowerCase())){
                bookAuthorList.add(i);
            }
        }
        return bookAuthorList;
    }
    public static ArrayList searchByTitle(String inputtitle){
        ArrayList<Book> bookTitleList = new ArrayList<>();
        for (Book i : listOfBooks){
            if (i.getAuthor().toLowerCase().contains(inputtitle.toLowerCase())){
                bookTitleList.add(i);
            }
        }
        return bookTitleList;
    }
}



