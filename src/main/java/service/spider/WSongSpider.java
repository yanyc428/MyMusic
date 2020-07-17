package service.spider;

import dao.database.SingersTableActions;
import dao.database.SongsTableActions;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.OpenWebDriver;
import utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WSongSpider implements  SongSpider{


    private WebDriver driver;
    private WebDriverWait wait;
    /**
     * 构造方法
     * @param driver 默认浏览器 Chrome使用无头模式
     */
    public WSongSpider(WebDriver driver) {
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
        String singerName;
        String singerUrl;
        String songUrl;
        String albumUrl;
        int singerId;
        List<WebElement> elements;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
        String url = "https://music.163.com/#/search/m/?s=" + word.replaceAll(" ", "%20") + "&type=1";


        try {
            this.driver.get(url);
            Thread.sleep(1000); //至关重要 不能删
            this.driver.switchTo().frame("contentFrame");
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("srchsongst")));
            elements = this.driver.findElement(By.className("srchsongst")).findElements(By.className("f-cb"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("检索异常  检索词:" + word);
            return mapList;
        }

        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                songName = elements.get(i).findElement(By.className("w0")).findElement(By.tagName("b")).getAttribute("title");
                map.put("name", songName);
                Log.log("歌曲名:" + songName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲名获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                songUrl = elements.get(i).findElement(By.className("w0")).findElement(By.tagName("a")).getAttribute("href");
                map.put("url", songUrl);
                Log.log("歌曲url:"+ songUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲url获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                singerName = elements.get(i).findElement(By.className("w1")).findElement(By.tagName("a")).getText();
                map.put("singerName", singerName);
                Log.log("歌手名:"+ singerName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌手名获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                singerUrl = elements.get(i).findElement(By.className("w1")).findElement(By.tagName("a")).getAttribute("href");
                map.put("singerUrl", singerUrl);
                Log.log("歌手url:"+ singerUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌手url获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                albumName = elements.get(i).findElement(By.className("w2")).findElement(By.tagName("a")).getAttribute("title");
                map.put("albumName", albumName);
                Log.log("专辑名:"+ albumName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑名获取失败  检索词:" + word + " "+i);
                continue;
            }

            try {
                albumUrl = elements.get(i).findElement(By.className("w2")).findElement(By.tagName("a")).getAttribute("href");
                map.put("albumUrl", albumUrl);
                Log.log("专辑url:"+ albumUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑url获取失败  检索词:" + word + " "+i);
                continue;
            }



            singerId = SingersTableActions.getIdByNameUrl(singerName, singerUrl);
            if (singerId == 0){
                SingersTableActions.singerInsert(singerName, Area.UNK, Letter.UNK, singerUrl, 1);
                singerId = SingersTableActions.getIdByNameUrl(singerName, singerUrl);
            }
            SongsTableActions.songInsert(songName, singerId, albumName, songUrl, albumUrl, 1);

            mapList.add(map);
        }

        return mapList;

    }

    public ArrayList<HashMap<String, String>> getSongByUrl(String url, int id){
        String songName;
        String albumName;
        String songUrl;
        String albumUrl;
        List<WebElement> elements;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();

        try {
            this.driver.get(url);
            this.driver.switchTo().frame("contentFrame");
            Thread.sleep(1000); //至关重要 不能删
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("m-table")));
            elements = this.driver.findElement(By.className("m-table")).findElements(By.tagName("tr"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("检索异常  url:" + url);
            return mapList;
        }

        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                songName = elements.get(i).findElement(By.className("txt")).findElement(By.tagName("b")).getAttribute("title");
                map.put("name", songName);
                Log.log("歌曲名:" + songName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲名获取失败  url:" + url + " "+i);
                continue;
            }

            try {
                songUrl = elements.get(i).findElement(By.className("txt")).findElement(By.tagName("a")).getAttribute("href");
                map.put("url", songUrl);
                Log.log("歌曲url:"+ songUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌曲url获取失败  url:" + url + " "+i);
                continue;
            }


            try {
                albumName = elements.get(i).findElement(By.className("text")).findElement(By.tagName("a")).getAttribute("title");
                map.put("albumName", albumName);
                Log.log("专辑名:"+ albumName);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑名获取失败  url:" + url + " "+i);
                continue;
            }

            try {
                albumUrl = elements.get(i).findElement(By.className("text")).findElement(By.tagName("a")).getAttribute("href");
                map.put("albumUrl", albumUrl);
                Log.log("专辑url:"+ albumUrl);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("专辑url获取失败  url:" + url + " "+i);
                continue;
            }

            SongsTableActions.songInsert(songName, id, albumName, songUrl, albumUrl, 1);

            mapList.add(map);
        }

        return  mapList;
    }

    public void quit(){
        this.driver.quit();
    }
}
