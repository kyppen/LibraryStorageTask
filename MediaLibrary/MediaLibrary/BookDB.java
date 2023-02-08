package MediaLibrary;

import DatabaseConnection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDB {

    public static void fromDB() {
        try (Connection con = ConnectDB.getConnection()) {
            Statement stat = con.createStatement();
            String insertSql = "SELECT * FROM mediaLibrary.books";
            ResultSet result = stat.executeQuery(insertSql);
            int counter = 0;
            ArrayList<String> bookids = new ArrayList<>();
            for (Book i : Book.listOfBooks) {
                bookids.add(i.getId());
            }
            while (result.next()) {
                counter = counter + 1;
                if (!bookids.contains(result.getString("id"))) {
                    Book.addBookToList(result.getString("id"),
                            result.getString("title"),
                            result.getString("author"),
                            result.getString("pagecount"),
                            result.getString("genre"));
                }
            }
            //System.out.println(counter + " Books has been added from DB");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBookToDB(String title, String author, String pagecount, String genre){
        BookDB.writeBooksToDB();
        try(Connection con = ConnectDB.getConnection()){
            PreparedStatement stat = con.prepareStatement("INSERT INTO books(title, author, pagecount, genre)" +
                    "VALUES (?, ?, ?, ?)");
            stat.setString(1, title);
            stat.setString(2, author);
            stat.setString(3, pagecount);
            stat.setString(4, genre);
            stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeBookDB(Book book){

        try(Connection con = ConnectDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement("Delete from books where id = ?");
            Long idLong = Long.parseLong(book.getId());
            stmt.setLong(1, idLong);
            System.out.println(book + " has been removed from the DB");
            stmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeBooksToDB() {
        fromDB();
        ArrayList<String> booksInDB = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select id from books");
            while (result.next()) {
                booksInDB.add(result.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Book i : Book.listOfBooks) {
            if (!booksInDB.contains(i.getId())) {

                addBookToDB(i.getTitle(), i.getAuthor(), i.getPagecount(), i.getGenre());
            }
        }
    }
    public static void writeBookChangesToDB(Book bookToBeModified, String columinput) {
        writeBooksToDB();
        switch (columinput){
            case "title":{
                try (Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE books "
                            + "SET title = ? "
                            + "WHERE id = ?";
                    String titleString = bookToBeModified.getAuthor();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, bookToBeModified.getTitle());
                    prepared.setString(2, bookToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Title has been changed to " + titleString);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "author":{
                try (Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE books "
                            + "SET author = ? "
                            + "WHERE id = ?";
                    String authorString = bookToBeModified.getAuthor();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, bookToBeModified.getAuthor());
                    prepared.setString(2, bookToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Author has been changed to " + authorString);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "pagecount":{
                try(Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE books "
                            + "SET pagecount = ? "
                            + "WHERE id = ?";
                    long pagecountlong = Long.parseLong(bookToBeModified.getPagecount());
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, bookToBeModified.getPagecount());
                    prepared.setString(2, bookToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Pagecount has been changed to " + pagecountlong);

                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            }
            case "genre":{
                try(Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE books "
                            + "SET genre = ? "
                            + "WHERE id = ?";
                    String genreString = bookToBeModified.getGenre();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, bookToBeModified.getGenre());
                    prepared.setString(2, bookToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Genre has been changed to " + genreString);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            }
        }
    }
}
