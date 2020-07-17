package service.spider;

import enumItem.Area;
import enumItem.Letter;

import java.util.ArrayList;
import java.util.HashMap;

public interface SongSpider {

    ArrayList<HashMap<String, String>> spiderByRetrieval(String word);
    ArrayList<HashMap<String, String>> getSongByUrl(String url, int id);
    void quit();
}
