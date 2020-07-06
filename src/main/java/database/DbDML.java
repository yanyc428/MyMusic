package database;


import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbDML {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public static void executeSqlScript(Connection connection, String sqlScript){
        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlScript);
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 一次sql语句执行成功");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
