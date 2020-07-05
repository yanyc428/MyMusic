package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {

    private static String sqliteDriver = "org.sqlite.JDBC";
    private static String dbName = "src/main/resources/mymusic.db";
    private String url;

    public Db(){
        this.url = "jdbc:sqlite:" + dbName;
    }

    public Connection openDatabase(){
        try {
            Class.forName(sqliteDriver);
            Connection connection = DriverManager.getConnection(this.url);
            return connection;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase(Connection connection){
        try {
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
