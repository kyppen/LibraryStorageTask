import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieDB {

    public static void fromDB(){
        try (Connection con = ConnectDB.getConnection()){
            Statement stat = con.createStatement();
            String insertSql = "SELECT * FROM mediaLibrary.movies";
            ResultSet result = stat.executeQuery(insertSql);
            int counter = 0;
            ArrayList<String> movieids = new ArrayList<>();
            for (Movie i : Movie.listOfMovies) {
                movieids.add(i.getId());
            }
            while (result.next()){
                counter = counter + 1;
                if (!movieids.contains(result.getString("id"))){
                    Movie.addMovieToList(result.getString("id"),
                            result.getString("title"),
                            result.getString("director"),
                            result.getString("length"),
                            result.getString("genre"));
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addMovieToDB(String title, String director, String length, String genre){
        try(Connection con = ConnectDB.getConnection()){
            PreparedStatement stat = con.prepareStatement("INSERT INTO movies(title, director, length, genre)" +
                    "VALUES (?, ?, ?, ?)");
            stat.setString(1, title);
            stat.setString(2, director);
            stat.setString(3, length);
            stat.setString(4, genre);
            stat.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void removeMovieDB(Movie movie){

        try(Connection con = ConnectDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement("Delete from movies where id = ?");
            Long idLong = Long.parseLong(movie.getId());
            stmt.setLong(1, idLong);
            System.out.println(movie + " has been removed from the DB");
            stmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void writeMoviesToDB() {
        fromDB();
        ArrayList<String> moviesInDB = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select id from movies");
            while (result.next()) {
                moviesInDB.add(result.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Movie i : Movie.listOfMovies) {
            if (!moviesInDB.contains(i.getId())) {

                addMovieToDB(i.getTitle(), i.getDirector(), i.getLength(), i.getGenre());
            }
        }
    }
    public static void writeMovieChangesToDB(Movie movieToBeModified, String columinput) {
        writeMoviesToDB();
        switch (columinput){
            case "title":{
                try (Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE movies "
                            + "SET title = ? "
                            + "WHERE id = ?";
                    String titleString = movieToBeModified.getDirector();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, movieToBeModified.getTitle());
                    prepared.setString(2, movieToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Title has been changed to " + titleString);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "director":{
                try (Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE movies "
                            + "SET director = ? "
                            + "WHERE id = ?";
                    String directorString = movieToBeModified.getDirector();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, movieToBeModified.getDirector());
                    prepared.setString(2, movieToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Director has been changed to " + directorString);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "length":{
                try(Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE movies "
                            + "SET length = ? "
                            + "WHERE id = ?";
                    long lengthLong = Long.parseLong(movieToBeModified.getLength());
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, movieToBeModified.getLength());
                    prepared.setString(2, movieToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Length in minutes has been changed to " + lengthLong);

                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            }
            case "genre":{
                try(Connection con = ConnectDB.getConnection()){
                    String sqlInsert = "UPDATE movies "
                            + "SET genre = ? "
                            + "WHERE id = ?";
                    String genreString = movieToBeModified.getGenre();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, movieToBeModified.getGenre());
                    prepared.setString(2, movieToBeModified.getId());
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
