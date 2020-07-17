package dao.database;

import enumItem.Area;
import enumItem.Letter;
import enumItem.Table;
import utils.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoveTableActions {

    private  static Connection connection = Db.openDatabase();

    @Override
    protected void finalize() {
        Db.closeDatabase(connection);
    }

    /**
     * 创建singers表
     */
    public static void loveCreate(){
        DbDDL.tableCreate(connection, Table.love);
    }

    /**
     * 删除singers表
     */
    public static void loveDrop(){
        DbDDL.tableDelete(connection, Table.love);
    }

    public static boolean loveInsert(int userId, int songId){

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "INSERT INTO love(user_id,song_id) VALUES (" +
                            userId + "," +
                            songId + ");");
            Log.log(userId + " " + songId +"喜欢成功");
            return true;
        } catch (SQLException throwables) {
            Log.log(userId + " " + songId  + "已喜欢");
            return  false;
        }

    }

    public static boolean loveDelete(int userId, int songId){

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                            "DELETE FROM love " +
                            "WHERE USER_ID = " + userId + " " +
                            "AND SONG_ID = "+ songId + ";");
            Log.log(userId + " " + songId +"取消喜欢成功");
            return true;
        } catch (SQLException throwables) {
            Log.log(userId + " " + songId  + "没有喜欢");
            return false;
        }

    }

    public static boolean check(int userId, int songId){
        try {
            ResultSet set = DbDML.executeReturnSqlScript(connection,
                    "SELECT * FROM LOVE WHERE USER_ID = " + userId + " AND SONG_ID = " + songId + ";");
            if (set.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static ArrayList<HashMap<String, String>> selectLove(int id){
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        ResultSet set;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT id, user_id, song_id FROM LOVE " +
                            "WHERE user_id = " + id + ";");

            while(set.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                HashMap<String, String> songMap = SongsTableActions.selectNameUrlAlbumById(set.getInt("song_id"));

                map.put("id", set.getString("id"));
                map.put("songId", songMap.get("id"));
                map.put("songName", songMap.get("songName"));
                map.put("songUrl", songMap.get("songUrl"));
                map.put("singerName", songMap.get("singerName"));
                map.put("singerUrl", songMap.get("singerUrl"));
                map.put("albumName", songMap.get("album"));
                map.put("albumUrl", songMap.get("album_url"));

                mapList.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mapList;
    }
}
