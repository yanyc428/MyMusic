package database;

import enumItem.Table;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbDDL {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    private static String createSingerTable = "create table singers(\n" +
            "id INTEGER primary key AUTOINCREMENT,  \n" +
            "name varchar(20) not null,  \n" +
            "type int not null,  \n" +
            "first_letter varchar(5) not null, \n" +
            "photograph varchar(50) null,  \n" +
            "url varchar(50) null,  \n" +
            "create_time timestamp not null DEFAULT (datetime('now','localtime')),  \n" +
            "update_time timestamp not null DEFAULT (datetime('now','localtime'))\n" +
            ");";

    private static String deleteSingerTable = "drop table if exists singers;";


    public static void tableCreate(Connection connection, Table table){
        try {
            Statement statement = connection.createStatement();
            switch (table.ordinal()){
                case 0:
                    statement.execute(createSingerTable);
                    break;
            }
            System.out.println(df.format(new Date())+" " + Thread.currentThread().toString() + " " +table.toString() + "创建成功");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void tableDelete(Connection connection, Table table){

        try {
            Statement statement = connection.createStatement();
            switch (table.ordinal()){
                case 0:
                    statement.execute(deleteSingerTable);
                    break;
            }
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " " + table.toString() + "删除成功");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
