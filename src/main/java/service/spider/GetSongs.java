package service.spider;

import dao.database.SingersTableActions;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Platform;
import utils.ListGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GetSongs {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 200, 24, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(50));

        ArrayList<HashMap<String,String>> list;
        ListGenerator<HashMap<String,String>> generator;

        for (Area area: Area.values()) {
            for (Platform platform: Platform.values()){
                list = SingersTableActions.selectAll(platform, area);
                generator= new ListGenerator<HashMap<String, String>>(list, 1000);
                while (generator.next()){
                    executor.execute(new SongSpiderThread(Browser.CHROME, platform, generator.get()));
                }
            }

        }

        System.out.println(executor.getActiveCount());
        System.out.println(executor.getPoolSize());




    }
}
