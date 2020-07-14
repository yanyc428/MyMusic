package dao.database;



import utils.DateUtil;
import utils.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbDML {

    /**
     * 执行没有返回的sql语句
     * @param connection 连接对象
     * @param sqlScript sql语句
     */
    public static void executeNoneReturnSqlScript(Connection connection, String sqlScript){
        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlScript);
            Log.log("一次sql语句执行成功(DDL/insert/delete/update)");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 执行有返回的sql语句 select
     * @param connection 连接对象
     * @param sqlScript sql语句
     * @return 结果集
     */
    public static ResultSet executeReturnSqlScript(Connection connection, String sqlScript){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlScript);
            Log.log("一次select语句执行成功");
            return resultSet;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
