package database;


import java.sql.Connection;
import java.sql.Statement;

public class DbDML {

    public static void executeSqlScript(Connection connection, String sqlScript){
        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlScript);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
