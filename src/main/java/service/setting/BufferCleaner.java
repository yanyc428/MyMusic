package service.setting;

import dao.database.SingersTableActions;
import dao.database.SongsTableActions;

public class BufferCleaner {


    public static void clean(){
        SingersTableActions.singersDrop();
        SingersTableActions.singersCreate();

        SongsTableActions.songsDrop();
        SongsTableActions.songsCreate();
    }


}
