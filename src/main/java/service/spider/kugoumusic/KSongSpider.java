package service.spider.kugoumusic;

import dao.database.SongsTableActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.spider.SongSpider;
import utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KSongSpider implements SongSpider {


    private WebDriver driver;
    private WebDriverWait wait;
    /**
     * 构造方法
     * @param driver 默认浏览器 Chrome使用无头模式
     */
    public KSongSpider(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * 得到当前的driver
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }
    public ArrayList<HashMap<String, String>> spiderByRetrieval(String word){
        String songName;
        String albumName;
        String albumUrl;
        List<WebElement> elements;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
        String url = "https://www.kugou.com/yy/html/search.html#searchType=song&searchKeyWord=" + word.replaceAll(" ", "%20");


        try {
            this.driver.get(url);
            Thread.sleep(1000); //至关重要 不能删
            elements = this.driver.findElement(By.id("search_song")).findElement(By.className("song_list")).findElement(By.className("list_content")).findElements(By.tagName("li"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("检索异常  检索词:" + word);
            return mapList;
        }
        System.out.println(elements.size());
        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                songName = elements.get(i).findElement(By.className("song_name")).getAttribute("title");
                map.put("songName", songName);
                map.put("songUrl", url);
                Log.log("歌曲名:" + songName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲名获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                albumName = elements.get(i).findElement(By.className("album_name")).getAttribute("title");
                map.put("albumName", albumName);
                Log.log("专辑名:"+ albumName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑名获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                albumUrl = elements.get(i).findElement(By.className("album_name")).getAttribute("href");
                map.put("albumUrl", albumUrl);
                Log.log("专辑url:"+ albumUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑url获取失败  检索词:" + word + " "+i);
                continue;
            }
            map.put("singerUrl", "");
            map.put("singerName", "");

            SongsTableActions.songInsert(songName, -1, albumName, url, albumUrl, 2);

            mapList.add(map);
        }

        return mapList;

    }

    public ArrayList<HashMap<String, String>> getSongByUrl(String url, int id){
        String songName;
        List<WebElement> elements;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();

        try {
            this.driver.get(url);
            Thread.sleep(1000); //至关重要 不能删
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("song_container")));
            elements = this.driver.findElement(By.id("song_container")).findElements(By.tagName("li"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("检索异常  url:" + url);
            return mapList;
        }

        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                songName = elements.get(i).findElement(By.tagName("a")).findElement(By.className("text")).getAttribute("title");
                map.put("name", songName);
                Log.log("歌曲名:" + songName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲名获取失败  url:" + url + " "+i);
                continue;
            }

            SongsTableActions.songInsert(songName, id, " ", url, " ", 2);

            mapList.add(map);
        }

        return  mapList;
    }

    public void quit(){
        this.driver.quit();
    }

}
