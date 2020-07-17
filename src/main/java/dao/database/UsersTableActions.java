package dao.database;

import enumItem.Table;
import enumItem.Users;
import utils.MD5Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTableActions {

    private  static Connection connection = Db.openDatabase();

    @Override
    protected void finalize() {
        Db.closeDatabase(connection);
    }

    public static void usersCreate(){
        DbDDL.tableCreate(connection, Table.users);
    }

    public static void usersDrop(){
        DbDDL.tableDelete(connection, Table.users);
    }

    public static int getIdByUsernamePassword(String userName, String password){
        int id = 0;
        ResultSet set = null;
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password, id "+
                            "FROM users " +
                            "WHERE user_name = " + "'" + userName + "';");
            while (set.next()){
                if (password.equals(set.getString("password"))){
                    id = set.getInt("id");
                    set.close();
                    return id;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public static int getIdByEmailPassword(String eMail, String password){
        int id = 0;
        ResultSet set = null;
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password, id "+
                            "FROM users " +
                            "WHERE e_mail = " +  "'" + eMail + "';");
            while (set.next()){
                if (password.equals(set.getString("password"))){
                    id = set.getInt("id");
                    set.close();
                    return id;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }


    public static void addUser(String userName, String eMail, String password, Integer gender, String avatar){
        Db db = new Db();
        Connection connection = db.openDatabase();

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                    "INSERT INTO users(user_name,e_mail,password,gender, avatar) VALUES (" +
                            "'" + userName + "'," +
                            "'" + eMail + "'," +
                            "'" + MD5Utils.code(password) + "'," +
                            gender + "," +
                            "'"+  avatar + "'"+
                            ");");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        db.closeDatabase(connection);
    }
    
    public static String getAvatar(int id) {
    	Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;
        
        String string = "";
        
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  avatar "+
                            "FROM users " +
                            "WHERE id = " +  id +" ;");
            set.next();
            string = set.getString("avatar");
            set.close();
            db.closeDatabase(connection);
            return string;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.closeDatabase(connection);
        return string;
	}

    public static String getName(int id) {
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        String string = "";

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  user_name "+
                            "FROM users " +
                            "WHERE id = " +  id +";");
            set.next();
            string = set.getString("user_name");
            set.close();
            db.closeDatabase(connection);
            return string;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.closeDatabase(connection);
        return string;
    }

    public static void updateLogin(int id) {
        Db db = new Db();
        Connection connection = db.openDatabase();

        try {
            DbDML.executeNoneReturnSqlScript(connection,
                        "UPDATE USERS SET LAST_LOGIN = now() "+
                                "WHERE id = " + id + ";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        db.closeDatabase(connection);
    }
}
