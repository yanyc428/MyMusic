package dao.database;

import utils.Log;

import java.sql.Connection;
import java.sql.DriverManager;


public class Db {

//    private static String sqliteDriver = "org.sqlite.JDBC";
//    private String dbName = System.getProperty("user.dir")+ "/resources/db/mymusic.db";
//    private String url;

    private static String sqliteDriver = "org.h2.Driver";
    private static String dbName = System.getProperty("user.dir")+ "/resources/db/mymusich2.db";
    private static String url = "jdbc:h2:file:" + dbName ;

    /**
     * 打开数据库连接 返回一个connection
     * @return connection
     */
    public static Connection openDatabase(){
        try {
            Class.forName(sqliteDriver);
            Connection connection = DriverManager.getConnection(url, "root", "root");
            Log.log("数据库连接成功");
            return connection;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     * @param connection 要关闭的连接
     */
    public static void closeDatabase(Connection connection){
        try {
            connection.close();
            Log.log("数据库关闭成功");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
