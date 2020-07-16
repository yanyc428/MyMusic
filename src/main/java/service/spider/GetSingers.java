package service.spider;

import enumItem.Area;
import enumItem.Browser;
import enumItem.Platform;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GetSingers {

    public static void get(Browser browser){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 2, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(50));

        for (Area area: Area.values()) {
            executor.execute(new SingerSpiderThread(browser, area, Platform.QQMuisc));
            executor.execute(new SingerSpiderThread(browser, area, Platform.WangYiYunMusic));
            executor.execute(new SingerSpiderThread(browser, area, Platform.KuGouMusic));
        }

    }

    public static void main(String[] args) {
        get(Browser.CHROME);

    }
}
