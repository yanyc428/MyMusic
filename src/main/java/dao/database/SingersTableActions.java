package dao.database;

import enumItem.Area;
import enumItem.Letter;
import enumItem.Singers;
import enumItem.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SingersTableActions {

    /**
     * 创建singers表
     */
    public static void singersCreate(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableCreate(connection, Table.singers);
        db.closeDatabase(connection);
    }

    /**
     * 删除singers表
     */
    public static void singersDrop(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableDelete(connection, Table.singers);
        db.closeDatabase(connection);
    }

    /**
     * 插入一个歌手
     * @param name 歌手名
     * @param area 歌手地区 type
     * @param letter 歌手首字母
     * @param url 歌手首页url
     */
    public static void singerInsert(String name, Area area, Letter letter, String url){

        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "INSERT INTO singers(name,type,first_letter,photograph, url) VALUES (" +
                        "'" +  name + "'," +
                         + area.number() + "," +
                        "'" +  letter.toString() + "'," +
                        "'src/main/resources/singer_photo/"+ name+".png'," +
                        "'"+ url + "');");

        db.closeDatabase(connection);
    }

    public static void singerUpdate(String name){
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "UPDATE singers " +
                    " SET update_time =  datetime('now', 'localtime')" +
                        "WHERE name = '" + name + "';");

        db.closeDatabase(connection);
    }

    public static void singerUpdate(Integer id){
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "UPDATE singers " +
                        " SET update_time =  datetime('now', 'localtime')" +
                        "WHERE id = " + id);

        db.closeDatabase(connection);
    }

    public static List<String> singerSelectName(Singers column, Integer param){
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        switch (column.ordinal()){
            case 0:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE id = " + param);
                break;
            case 2:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE type = " + param);
                break;
        }

        List<String> list = new ArrayList<String>();
        try {
            while (set.next()){
                list.add(set.getString("name"));
            }
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return null;
    }

    public static List<String> singerSelectName(Singers column, String param){
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        switch (column.ordinal()){
            case 1:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE name = '" + param + "';");
                break;
            case 3:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE letter = '" + param + "';");
                break;
            case 4:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE url = '" + param + "';");
                break;
        }

        List<String> list = new ArrayList<String>();
        try {
            while (set.next()){
                list.add(set.getString("name"));
            }
            db.closeDatabase(connection);
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return null;
    }

    public static List<String> singerSelectArea(Singers column, String param){
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        switch (column.ordinal()){
            case 1:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE name = '" + param + "';");
                break;
            case 3:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE letter = '" + param + "';");
                break;
            case 4:
                set = DbDML.executeReturnSqlScript(connection,
                        "SELECT  name "+
                                "FROM singers " +
                                "WHERE url = '" + param + "';");
                break;
        }

        List<String> list = new ArrayList<String>();
        try {
            while (set.next()){
                list.add(set.getString("name"));
            }
            db.closeDatabase(connection);
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return null;
    }

    public static Integer singsCount() {
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;
        try {
            set = DbDML.executeReturnSqlScript(connection," SELECT count(*) as seq " +
                    "FROM singers;");
            db.closeDatabase(connection);
            return Integer.parseInt(set.getString("seq"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return 0;
    }

    public static String singerSelectUrlById(Integer param){
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  url "+
                            "FROM singers " +
                            "WHERE id = " + param);



            String s = set.getString("url");
            set.close();
            db.closeDatabase(connection);
            return s;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(param +"号歌手爬取失败");
        }

        db.closeDatabase(connection);
        return null;
    }
}
