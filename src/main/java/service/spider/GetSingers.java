package service.spider;

import enumItem.Area;
import enumItem.Browser;

public class GetSingers {

    public static void get(Browser browser){

        for (Area area: Area.values()) {
            new SingerSpider(browser, area).start();
        }

    }

    public static void main(String[] args) {
        get(Browser.CHROME);
    }
}
