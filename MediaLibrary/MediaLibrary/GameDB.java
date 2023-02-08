package MediaLibrary;
import DatabaseConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameDB {
    public static void fromDB() {
        try (Connection con = ConnectDB.getConnection()) {
            Statement stat = con.createStatement();
            String insertSql = "SELECT * FROM mediaLibrary.games";
            ResultSet result = stat.executeQuery(insertSql);
            int counter = 0;
            ArrayList<String> gameids = new ArrayList<>();
            for (Game i : Game.listOfGames) {
                gameids.add(i.getId());
            }
            while (result.next()) {
                counter = counter + 1;
                if (!gameids.contains(result.getString("id"))) {
                    Game.addGamesToList(result.getString("id"),
                            result.getString("title"),
                            result.getString("storywriter"),
                            result.getString("publishingcompany"),
                            result.getString("genre"));
                }
            }
            //System.out.println(counter + " Books has been added from DB");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addGameToDB(String title, String storywriter, String publishingcompany, String genre) {
        GameDB.writeGamesToDB();
        try (Connection con = ConnectDB.getConnection()) {
            PreparedStatement stat = con.prepareStatement("INSERT INTO games(title, storywriter, publishingcompany, genre)" +
                    "VALUES (?, ?, ?, ?)");
            stat.setString(1, title);
            stat.setString(2, storywriter);
            stat.setString(3, publishingcompany);
            stat.setString(4, genre);
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        fromDB();
    }

    public static void removeGameDB(Game game) {

        try (Connection con = ConnectDB.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("Delete from games where id = ?");
            Long idLong = Long.parseLong(game.getId());
            stmt.setLong(1, idLong);
            System.out.println(game + " has been removed from the DB");
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeGamesToDB() {
        fromDB();
        ArrayList<String> gamesInDb = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select id from games");
            while (result.next()) {
                gamesInDb.add(result.getString("id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Game i : Game.listOfGames) {
            if (!gamesInDb.contains(i.getId())) {
                addGameToDB(i.getTitle(), i.getStorywriter(), i.getPublishingcompany(), i.getGenre());
            }
        }
    }

    public static void writeGameChangesToDB(Game gameToBeModified, String columinput) {
        writeGamesToDB();
        switch (columinput) {
            case "title": {
                try (Connection con = ConnectDB.getConnection()) {
                    String sqlInsert = "UPDATE games "
                            + "SET title = ? "
                            + "WHERE id = ?";
                    String titleString = gameToBeModified.getTitle();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, gameToBeModified.getTitle());
                    prepared.setString(2, gameToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Title has been changed to " + titleString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "storywriter": {
                try (Connection con = ConnectDB.getConnection()) {
                    String sqlInsert = "UPDATE games "
                            + "SET storywriter = ? "
                            + "WHERE id = ?";
                    String storywriterString = gameToBeModified.getStorywriter();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, gameToBeModified.getStorywriter());
                    prepared.setString(2, gameToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Director has been changed to " + storywriterString);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "publishingcompany": {
                try (Connection con = ConnectDB.getConnection()) {
                    String sqlInsert = "UPDATE games "
                            + "SET publishingcompany = ? "
                            + "WHERE id = ?";
                    String publishingcompanyString = gameToBeModified.getPublishingcompany();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, gameToBeModified.getPublishingcompany());
                    prepared.setString(2, gameToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Director has been changed to " + publishingcompanyString);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "genre": {
                try (Connection con = ConnectDB.getConnection()) {
                    String sqlInsert = "UPDATE games "
                            + "SET genre = ? "
                            + "WHERE id = ?";
                    String genreString = gameToBeModified.getGenre();
                    PreparedStatement prepared = con.prepareStatement(sqlInsert);
                    prepared.setString(1, gameToBeModified.getGenre());
                    prepared.setString(2, gameToBeModified.getId());
                    prepared.executeUpdate();
                    prepared.close();
                    System.out.println("Genre has been changed to " + genreString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            }
        }
    }
}
