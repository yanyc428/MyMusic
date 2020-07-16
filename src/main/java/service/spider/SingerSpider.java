package service.spider;

import enumItem.Area;
import enumItem.Letter;

import java.util.ArrayList;
import java.util.HashMap;

public interface SingerSpider {
    ArrayList<HashMap<String, String>> spider(Area area, Letter letter);
    ArrayList<HashMap<String, String>> getSingers(String url, Area area, Letter letter);
    void quit();

}
