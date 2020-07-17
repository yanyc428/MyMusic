package service;

import dao.database.UsersTableActions;
import utils.MD5Utils;

public class LoginService {

    public static int checkUser(String userName, String eMail, String password){
        int id = 0;
        password = MD5Utils.code(password);
        id = UsersTableActions.getIdByUsernamePassword(userName, password);
        if (id ==0){
            id = UsersTableActions.getIdByEmailPassword(eMail, password);
        }
        return id;
    }
}
