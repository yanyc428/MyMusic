package dao.database;

import enumItem.*;
import utils.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SingersTableActions {

    private  static Connection connection = Db.openDatabase();

    @Override
    protected void finalize() {
        Db.closeDatabase(connection);
    }

    /**
     * 创建singers表
     */
    public static void singersCreate(){
        DbDDL.tableCreate(connection, Table.singers);
    }

    /**
     * 删除singers表
     */
    public static void singersDrop(){
        DbDDL.tableDelete(connection, Table.singers);
    }

    /**
     * 插入一个歌手
     * @param name 歌手名
     * @param area 歌手地区 type
     * @param letter 歌手首字母
     * @param url 歌手首页url
     */
    public static void singerInsert(String name, Area area, Letter letter, String url, int source){

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "INSERT INTO singers(name,type,first_letter, url, source) VALUES (" +
                            "'" +  name.replaceAll("'", "''") + "'," +
                             + area.number() + "," +
                            "'" +  letter.toString() + "'," +
                            "'"+ url + "'," +
                            + source +");");
            Log.log(name +"数据插入成功");
        } catch (SQLException throwables) {
            Log.log(name + url + "已存在");
            updateTime(name, url);
        }

    }

    public static void singerUpdate(String name){

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE singers " +
                        " SET update_time =  datetime('now', 'localtime')" +
                            "WHERE name = '" + name + "';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void singerUpdate(Integer id){
        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE singers " +
                            " SET update_time =  datetime('now', 'localtime')" +
                            "WHERE id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static List<String> singerSelectName(Singers column, Integer param){
        ResultSet set = null;

        switch (column.ordinal()){
            case 0:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE id = " + param);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 2:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE type = " + param);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
        return null;
    }

    public static List<String> singerSelectName(Singers column, String param){
        ResultSet set = null;

        switch (column.ordinal()){
            case 1:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE name = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 3:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE letter = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 4:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE url = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
        return null;
    }

    public static List<String> singerSelectArea(Singers column, String param){
        ResultSet set = null;

        switch (column.ordinal()){
            case 1:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE name = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 3:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE letter = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 4:
                try {
                    set = DbDML.executeReturnSqlScript(connection,
                            "SELECT  name "+
                                    "FROM singers " +
                                    "WHERE url = '" + param + "';");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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


        return null;
    }

    public static Integer singsCount() {
        ResultSet set = null;
        try {
            set = DbDML.executeReturnSqlScript(connection," SELECT count(*) as seq " +
                    "FROM singers;");
            return Integer.parseInt(set.getString("seq"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    
    public static HashMap<String, String> singerSelectUrlNameById(Integer id){
        ResultSet set = null;
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  name, url "+
                            "FROM singers " +
                            "WHERE id = " + id);
            
            if (set.next()) {
				map.put("name", set.getString("name"));
				map.put("url", set.getString("url"));
			}
            set.close();
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            Log.error("暂无此人"  + id);
        }

        return map;
    }
    
    
    public static void updateTime(String name, String URL){
        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE SINGERS SET UPDATE_TIME = now() "+
                            "WHERE name = '" + name.replaceAll("'", "''") + "' " +
                            "and url = '" + URL + "';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getIdByNameUrl(String name, String URL){
        ResultSet set = null;
        int number = 0;
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  id "+
                            "FROM singers " +
                            "WHERE name = '" + name + "' " +
                            "AND url = '" + URL + "';");
            if (set.next()){
                number = set.getInt("id");
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.error("无此歌手" + name);
        }
        return number;
    }

    public static ArrayList<HashMap<String, String>> selectOffline(Area area, Letter letter, Platform platform){
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        ResultSet set;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT NAME, URL FROM SINGERS " +
                            "WHERE type = " + area.number() + " " +
                            " AND first_letter = '" + letter.toString() + "' "+
                            " and source = "+ platform.ordinal() + ";");

            while(set.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", set.getString("NAME"));
                map.put("url", set.getString("URL"));
                mapList.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mapList;
    }

    public static ArrayList<HashMap<String, String>> selectAll(Platform platform, Area area){
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        ResultSet set;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT ID, URL FROM SINGERS " +
                            "WHERE source = "+ platform.ordinal() + " and " +
                            "type = " + area.number() + ";");

            while(set.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", set.getString("ID"));
                map.put("url", set.getString("URL"));
                map.put("platform", platform.toString());
                mapList.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mapList;
    }

    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        mapList = selectOffline(Area.CHN_G, Letter.A, Platform.WangYiYunMusic);
        for (HashMap<String, String> item :
                mapList) {
            System.out.println(item);
        }
    }
}
