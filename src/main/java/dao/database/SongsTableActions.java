package dao.database;

import enumItem.Area;
import enumItem.Letter;
import enumItem.Singers;
import enumItem.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SongsTableActions {

    public static void songsCreate(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableCreate(connection, Table.songs);
        db.closeDatabase(connection);
    }

    public static void songsDrop(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableDelete(connection, Table.songs);
        db.closeDatabase(connection);
    }

    public static void songInsert(String name, Integer singerId, String album, String url, String lyric, Integer source){

        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "INSERT INTO songs(name,singer_id, album, url,lyric, photo, source) VALUES " +
                        "(" +
                        "'" + name + "'," +
                        singerId + "," +
                        "'" + album + "'," +
                        "'" +  url + "'," +
                        "'" + lyric + "'," +
                        "'src/main/resources/song_photo/" + singerId + album + lyric.substring(0,2) +".png'," +
                        + source +
                        ");");

        db.closeDatabase(connection);
    }

    public static void songUpdate(String name){
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "UPDATE songs " +
                    " SET update_time =  datetime('now', 'localtime')" +
                        "WHERE name = " + name);

        db.closeDatabase(connection);
    }

    public static void songUpdate(Integer id){
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "UPDATE songs " +
                        " SET update_time =  datetime('now', 'localtime')" +
                        "WHERE id = " + id);

        db.closeDatabase(connection);
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
