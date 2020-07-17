package dao.database;

import enumItem.Area;
import enumItem.Letter;
import enumItem.Singers;
import enumItem.Table;
import utils.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsTableActions {

    private  static Connection connection = Db.openDatabase();

    @Override
    protected void finalize() {
        Db.closeDatabase(connection);
    }



    public static void songsCreate(){
        DbDDL.tableCreate(connection, Table.songs);
    }

    public static void songsDrop(){
        DbDDL.tableDelete(connection, Table.songs);
    }

    public static void songInsert(String name, Integer singerId, String album, String url, String albumUrl, Integer source){
        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "INSERT INTO songs(name,singer_id, album, url,album_url,source) VALUES " +
                            "(" +
                            "'" + name.replaceAll("'", "''") + "'," +
                            singerId + "," +
                            "'" + album.replaceAll("'", "''") + "'," +
                            "'" +  url + "'," +
                            "'" + albumUrl + "'," +
                            + source +
                            ");");
            Log.log(name +"数据插入成功");
        } catch (SQLException throwables) {
            Log.log(name + url + "已存在");
            updateTime(name, url);
        }
    }

    public static void updateTime(String name, String url){
        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE SONGS SET UPDATE_TIME = now() "+
                            "WHERE name = '" + name.replaceAll("'", "''") + "' " +
                            "and url = '" + url + "';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void songUpdate(Integer id){
        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE songs " +
                            " SET update_time =  datetime('now', 'localtime')" +
                            "WHERE id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
//
//    public static List<String> singerSelectName(Singers column, Integer param){
//        Db db = new Db();
//        Connection connection = db.openDatabase();
//        ResultSet set = null;
//
//        switch (column.ordinal()){
//            case 0:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE id = " + param);
//                break;
//            case 2:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE type = " + param);
//                break;
//        }
//
//        List<String> list = new ArrayList<String>();
//        try {
//            while (set.next()){
//                list.add(set.getString("name"));
//            }
//            return list;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        db.closeDatabase(connection);
//        return null;
//    }
//
//    public static List<String> singerSelectName(Singers column, String param){
//        Db db = new Db();
//        Connection connection = db.openDatabase();
//        ResultSet set = null;
//
//        switch (column.ordinal()){
//            case 1:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE name = " + param);
//                break;
//            case 3:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE letter = " + param);
//                break;
//            case 4:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE url = " + param);
//                break;
//        }
//
//        List<String> list = new ArrayList<String>();
//        try {
//            while (set.next()){
//                list.add(set.getString("name"));
//            }
//            return list;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        db.closeDatabase(connection);
//        return null;
//    }
//
//    public static List<String> singerSelectArea(Singers column, String param){
//        Db db = new Db();
//        Connection connection = db.openDatabase();
//        ResultSet set = null;
//
//        switch (column.ordinal()){
//            case 1:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE name = " + param);
//                break;
//            case 3:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE letter = " + param);
//                break;
//            case 4:
//                set = DbDML.executeReturnSqlScript(connection,
//                        "SELECT  name"+
//                                "FROM singers " +
//                                "WHERE url = " + param);
//                break;
//        }
//
//        List<String> list = new ArrayList<String>();
//        try {
//            while (set.next()){
//                list.add(set.getString("name"));
//            }
//            return list;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        db.closeDatabase(connection);
//        return null;
//    }

}
