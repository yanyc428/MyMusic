package service.setting;

import dao.database.SingersTableActions;
import dao.database.SongsTableActions;
import utils.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferCleaner {


    public static void clean(){
        SingersTableActions.singersDrop();
        SingersTableActions.singersCreate();

        SongsTableActions.songsDrop();
        SongsTableActions.songsCreate();

        cleanLog(Log.fileName);
        cleanLog(Log.exception);

        Log.log("缓存清理完毕！");

    }

    private static void cleanLog(String fileName){
        File file = new File(System.getProperty("user.dir") + "/log/" + fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            Log.log(fileName + " 文件内容已清空！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
