package dao.database;

import enumItem.Table;
import utils.Log;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbDDL {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    // for sqlite
//    private static String createSingerTable = "create table singers(\n" +
//            "id INTEGER primary key AUTOINCREMENT,  \n" +
//            "name varchar(20) not null,  \n" +
//            "type int not null,  \n" +
//            "first_letter varchar(5) not null, \n" +
//            "photograph varchar(50) null,  \n" +
//            "url varchar(50) null,  \n" +
//            "create_time timestamp not null DEFAULT (datetime('now','localtime')),  \n" +
//            "update_time timestamp not null DEFAULT (datetime('now','localtime'))\n" +
//            ");";

    // for h2
    private static String createSingerTable = "create table singers\n" +
            "(\n" +
            "\tid int auto_increment,\n" +
            "\tname varchar(20) not null,\n" +
            "\ttype int not null,\n" +
            "\tfirst_letter varchar(5) not null,\n" +
            "\tphotograph varchar(20) default null,\n" +
            "\turl varchar(50) not null,\n" +
            "\tcreate_time timestamp default now() not null,\n" +
            "\tupdate_time timestamp default now() not null,\n" +
            "\tconstraint SINGERS_PK\n" +
            "\t\tprimary key (id)\n" +
            ");";

//    for sqlite
//    private static String createSongsTable = "create table songs(\n" +
//            "\tid integer\n" +
//            "\t\tconstraint songs_pk\n" +
//            "\t\t\tprimary key autoincrement,\n" +
//            "\tname varchar(40) not null,\n" +
//            "\tsinger_id integer\n" +
//            "\t\tconstraint songs_singers_id_fk\n" +
//            "\t\t\treferences singers,\n" +
//            "\talbum varchar(20) default null,\n" +
//            "\tsource integer not null,\n" +
//            "\turl varchar(100) not null,\n" +
//            "\tlyric text default null,\n" +
//            "\tphoto varchar(50) not null,\n" +
//            "\tcreate_time timestamp default (datetime('now','localtime')),\n" +
//            "\tupdate_time timestamp default (datetime('now','localtime')),\n" +
//            "\tconstraint songs_pk_2\n" +
//            "\t\tunique (name, singer_id, album, source)\n" +
//            ");";

//    for h2 database
    private static String createSongsTable = "create table songs\n" +
            "(\n" +
            "\tid int auto_increment,\n" +
            "\tname varchar(40) not null,\n" +
            "\tsinger_id int not null,\n" +
            "\talbum varchar(20) default null,\n" +
            "\tsource int not null,\n" +
            "\turl varchar(100) not null,\n" +
            "\tlyric text default null,\n" +
            "\tcreate_time timestamp default now(),\n" +
            "\tupdate_time timestamp default now(),\n" +
            "\tconstraint SONGS_PK\n" +
            "\t\tprimary key (id),\n" +
            "\tconstraint SONGS_PK_2\n" +
            "\t\tunique (name, singer_id, album, source)\n" +
            ");";

//    for sqlite
//    private static String createUsersTable = "create table users\n" +
//            "(\n" +
//            "\tid integer not null\n" +
//            "\t\tconstraint users_pk\n" +
//            "\t\t\tprimary key autoincrement,\n" +
//            "\tuser_name varchar(20) not null,\n" +
//            "\te_mail varchar(30) not null,\n" +
//            "\tpassword varchar(20) not null,\n" +
//            "\tgender integer not null,\n" +
//            "\tavatar varchar(20) not null,\n" +
//            "\tcreate_time timestamp default (datetime('now','localtime')),\n" +
//            "\tupdate_time timestamp default (datetime('now','localtime')),\n" +
//            "\tlast_login timestamp,\n" +
//            "\tconstraint users_pk_2\n" +
//            "\t\tunique (e_mail, user_name)\n" +
//            ");\n";

// for h2
    public static String createUsersTable = "create table users\n" +
            "(\n" +
            "\tid int auto_increment,\n" +
            "\tuser_name varchar(20) not null,\n" +
            "\te_mail varchar(30) not null,\n" +
            "\tpassword varchar(32) not null,\n" +
            "\tgender int not null,\n" +
            "\tavatar varchar(50) not null,\n" +
            "\tcreate_time timestamp default now(),\n" +
            "\tupdate_time timestamp default now(),\n" +
            "\tlast_login timestamp default now(),\n" +
            "\tconstraint TABLE_NAME_PK\n" +
            "\t\tprimary key (id),\n" +
            "\tconstraint TABLE_NAME_PK_2\n" +
            "\t\tunique (e_mail, user_name)\n" +
            ");\n";
    private static String deleteSingerTable = "drop table if exists singers;";

    private static String deleteSongsTable = "drop table if exists songs;";

    private static String deleteUsersTable = "drop table if exists users;";

    /**
     * 创建表
     * @param connection 数据库连接对象
     * @param table 操作表
     */
    public static void tableCreate(Connection connection, Table table){
        try {
            switch (table.ordinal()){
                case 0:
                    DbDML.executeNoneReturnSqlScript(connection, createSingerTable);
                    break;
                case 1:
                    DbDML.executeNoneReturnSqlScript(connection, createSongsTable);
                    break;
                case 2:
                    DbDML.executeNoneReturnSqlScript(connection, createUsersTable);
                    break;
            }
            Log.log(table.toString() + "创建成功");
        }
        catch (Exception e){
            Log.log(table.toString() + "创建失败");
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     * @param connection 数据库连接对象
     * @param table 操作表
     */
    public static void tableDelete(Connection connection, Table table){

        try {
            switch (table.ordinal()){
                case 0:
                    DbDML.executeNoneReturnSqlScript(connection, deleteSingerTable);
                    break;
                case 1:
                    DbDML.executeNoneReturnSqlScript(connection, deleteSongsTable);
                    break;
                case 2:
                    DbDML.executeNoneReturnSqlScript(connection, deleteUsersTable);
                    break;
            }
            Log.log(table.toString() + "删除成功");
        }
        catch (Exception e){
            Log.log(table.toString() + "删除失败");
            e.printStackTrace();
        }
    }
}
