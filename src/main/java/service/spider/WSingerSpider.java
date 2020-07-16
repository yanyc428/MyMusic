package service.spider;

import dao.database.SingersTableActions;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.OpenWebDriver;
import utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WSingerSpider {

    private WebDriver driver;
    private WebDriverWait wait;

    public WSingerSpider(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    public ArrayList<HashMap<String, String>> spider(Area area, Letter letter){
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
        try {

            String url = "https://music.163.com/#/discover/artist/cat?";
            switch (area.number()){
                case 11:
                    url = url + "id=1001&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 12:
                    url = url + "id=1002&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 13:
                    url = url + "id=1003&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 21:
                    url = url + "id=2001&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 22:
                    url = url + "id=2002&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 23:
                    url = url + "id=2003&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 31:
                    url = url + "id=6001&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 32:
                    url = url + "id=6002&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 33:
                    url = url + "id=6003&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 41:
                    url = url + "id=7001&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 42:
                    url = url + "id=7002&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 43:
                    url = url + "id=7003&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 51:
                    url = url + "id=4001&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 52:
                    url = url + "id=4002&initial=" + (int)letter.toString().charAt(0);
                    break;
                case 53:
                    url = url + "id=4003&initial=" + (int)letter.toString().charAt(0);
                    break;

            }
            System.out.println(url);
            Log.log("当前爬取 " + area.toString() + " 首字母 " + letter.toString());
            Log.log("爬取url:" + url);
            mapList.addAll(getSingers(url, area, letter));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mapList;
    }

    public ArrayList<HashMap<String, String>> getSingers(String url, Area area, Letter letter) {
        String name;
        String singer_url;
        String new_url = "";
        List<WebElement> elements;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();

        /**
         * 打开url 等待js加载
         */
        try {
            this.driver.get(url);
            Thread.sleep(5000);
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("m-artist-box")));
            elements = this.driver.findElements(By.tagName("p"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("url("+area.toString() +" "+ letter.toString() +")打开异常:" + url);
            return mapList;
        }

        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                name = elements.get(i).findElement(By.tagName("a")).getText();
                map.put("name", name);
                Log.log("歌手名:" + name);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌手名获取失败"+area.toString() +" "+ letter.toString()+" "+i);
                continue;
            }

            try {
                singer_url = elements.get(i).findElement(By.tagName("a")).getAttribute("href");
                map.put("url", singer_url);
                Log.log("歌手url:"+ singer_url);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌手url获取失败"+area.toString() +" "+ letter.toString()+" "+name);
                continue;
            }

            SingersTableActions.singerInsert(name, area, letter, singer_url, 0);

            mapList.add(map);
        }
        return mapList;
    }


    public void quit(){
        this.driver.quit();
    }


}
