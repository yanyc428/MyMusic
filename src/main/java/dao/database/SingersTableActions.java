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
import java.util.Date;
import java.util.List;

public class SingersTableActions {

    private  static Connection connection = Db.openDatabase();

    @Override
    protected void finalize() throws Throwable {
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
                            "'" +  name + "'," +
                             + area.number() + "," +
                            "'" +  letter.toString() + "'," +
                            "'"+ url + "'," +
                            + source +");");
            Log.log(name +"数据插入成功");
        } catch (SQLException throwables) {
            Log.log(name + url + "已存在");
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

    public static String singerSelectUrlById(Integer param){
        ResultSet set = null;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  url "+
                            "FROM singers " +
                            "WHERE id = " + param);
            String s = set.getString("url");
            set.close();
            return s;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(param +"号歌手爬取失败");
        }

        return null;
    }
}
