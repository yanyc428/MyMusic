package dao.database;

import enumItem.Table;
import enumItem.Users;
import utils.MD5Utils;

import java.sql.Connection;
import java.sql.ResultSet;

public class UsersTableActions {

    public static void usersCreate(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableCreate(connection, Table.users);
        db.closeDatabase(connection);
    }

    public static void usersDrop(){
        Db db = new Db();
        Connection connection = db.openDatabase();
        DbDDL.tableDelete(connection, Table.users);
        db.closeDatabase(connection);
    }

    public static boolean checkUser(String userName, String eMail, String password){
        Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;

        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password "+
                            "FROM users " +
                            "WHERE user_name = " + "'" + userName + "';");
            while (set.next()){
                if (MD5Utils.code(password).equals(set.getString("password"))){
                    set.close();
                    db.closeDatabase(connection);
                    return true;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {

            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password "+
                            "FROM users " +
                            "WHERE e_mail = " +  "'" + eMail + "';");
            while (set.next()){
                if (MD5Utils.code(password).equals(set.getString("password"))){
                    set.close();
                    db.closeDatabase(connection);
                    return true;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return false;
    }

    public static void addUser(String userName, String eMail, String password, Integer gender, String avatar){
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                "INSERT INTO users(user_name,e_mail,password,gender, avatar) VALUES (" +
                        "'" + userName + "'," +
                        "'" + eMail + "'," +
                        "'" + MD5Utils.code(password) + "'," +
                        gender + "," +
                        "'"+  avatar + "'"+
                        ");");

        db.closeDatabase(connection);
    }
    
    public static String getAvatar(String name, String password) {
    	Db db = new Db();
        Connection connection = db.openDatabase();
        ResultSet set = null;
        
        String string = "";
        
        try {
            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password, avatar "+
                            "FROM users " +
                            "WHERE user_name = " + "'" + name + "';");
            while (set.next()){
                if (MD5Utils.code(password).equals(set.getString("password"))){
                	string = set.getString("avatar");
                    set.close();
                    db.closeDatabase(connection);
                    return string;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {

            set = DbDML.executeReturnSqlScript(connection,
                    "SELECT  password, avatar "+
                            "FROM users " +
                            "WHERE e_mail = " +  "'" + name + "';");
            while (set.next()){
                if (MD5Utils.code(password).equals(set.getString("password"))){
                	string = set.getString("avatar");
                    set.close();
                    db.closeDatabase(connection);
                    return string;
                }
            }
            set.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        db.closeDatabase(connection);
        return string;
	}

    public static void updateLogin(String name, String password) {
        Db db = new Db();
        Connection connection = db.openDatabase();

        DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE USERS SET LAST_LOGIN = now() "+
                            "WHERE user_name = " + "'" + name + "' " +
                            "and password = '" + MD5Utils.code(password) +"';");

        DbDML.executeNoneReturnSqlScript(connection,
                    "UPDATE USERS SET LAST_LOGIN = now() "+
                            "WHERE e_mail = " +  "'" + name + "'" +
                            "and password = '" + MD5Utils.code(password) +"';");

        db.closeDatabase(connection);
    }
}
