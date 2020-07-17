package service;

import dao.database.LoveTableActions;

public class LoveAMusic {

    public static boolean love(int userId, int songId){
        if (LoveTableActions.loveInsert(userId, songId)){
            return true;
        }else{
            LoveTableActions.loveDelete(userId, songId);
            return false;
        }
    }

    public static boolean check(int userId, int songId){
        return LoveTableActions.check(userId, songId);
    }
}
