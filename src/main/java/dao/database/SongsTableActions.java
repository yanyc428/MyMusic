package dao.database;

import enumItem.Table;
import utils.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

    
    public static ArrayList<HashMap<String, String>> selectOffline(String word){
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        ResultSet set;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT id, name, url, singer_id, album, album_url  FROM SONGS " +
                            "WHERE name like '%" + word + "%' " +
                            " OR album like  '%" + word + "%'; ");

            while(set.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                HashMap<String, String> singerMap = SingersTableActions.singerSelectUrlNameById(set.getInt("singer_id"));
                map.put("id", set.getString("id"));
                map.put("songName", set.getString("name"));
                map.put("songUrl", set.getString("url"));
                map.put("singerName", singerMap.get("name"));
                map.put("singerUrl", singerMap.get("url"));
                map.put("albumName", set.getString("album"));
                map.put("albumUrl", set.getString("album_url"));
                mapList.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mapList;
    }

    public static int getIdByNameURLAlbumURL(String songName, String songUrl, String albumURL){
        ResultSet set;
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT id  FROM SONGS " +
                            "WHERE name = " + "'" + songName + "' and " +
                            "url = '" + songUrl + "' and "  +
                            "album_url = '" + albumURL + "';");
            if (set.next()){
                return set.getInt("id");
            }else{
                return 0;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static HashMap<String, String> selectNameUrlAlbumById(int id){
        ResultSet set;
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT id, name, url, singer_id, album, album_url  FROM SONGS " +
                            "WHERE id = " + id+";");
            if (set.next()){
                HashMap<String, String> singerMap = SingersTableActions.singerSelectUrlNameById(set.getInt("singer_id"));
                map.put("id", set.getInt("id")+"");
                map.put("songName", set.getString("name"));
                map.put("songUrl", set.getString("url"));
                map.put("album", set.getString("album"));
                map.put("albumUrl", set.getString("album_url"));
                map.put("singerName", singerMap.get("name"));
                map.put("singerUrl", singerMap.get("url"));
            }
            return map;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

}
