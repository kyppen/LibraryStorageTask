package MediaLibrary;
import DatabaseConnection.*;

import java.sql.Connection;
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

}
