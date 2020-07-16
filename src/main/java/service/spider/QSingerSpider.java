package service.spider;

import com.google.common.collect.HashBiMap;
import dao.database.Db;
import dao.database.DbDML;
import dao.database.SingersTableActions;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sun.jvm.hotspot.compiler.OopMapStream;
import utils.Log;
import utils.Logger;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;


public class QSingerSpider implements SingerSpider{
    private WebDriver driver;
    private WebDriverWait wait;

    public QSingerSpider(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    public ArrayList<HashMap<String, String>> spider(Area area, Letter letter){
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
        try {

            String url = "https://y.qq.com/portal/singer_list.html#genre=-100&";
            List<String> list = new ArrayList<String>();
            switch (area.number()){
                case 11:
                    list.add(url + "sex=0&area=200&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    list.add(url + "sex=0&area=2&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 12:
                    list.add(url + "sex=1&area=200&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    list.add(url + "sex=1&area=2&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 13:
                    list.add(url + "sex=2&area=200&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    list.add(url + "sex=2&area=2&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 21:
                    list.add(url + "sex=0&area=5&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 22:
                    list.add(url + "sex=1&area=5&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 23:
                    list.add(url + "sex=2&area=5&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 31:
                    list.add(url + "sex=0&area=4&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 32:
                    list.add(url + "sex=1&area=4&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 33:
                    list.add(url + "sex=2&area=4&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 41:
                    list.add(url + "sex=0&area=3&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 42:
                    list.add(url + "sex=1&area=3&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 43:
                    list.add(url + "sex=2&area=3&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 51:
                    list.add(url + "sex=0&area=6&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 52:
                    list.add(url + "sex=1&area=6&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;
                case 53:
                    list.add(url + "sex=2&area=6&page=1&"+ "index=" + (letter.ordinal() + 1) + "&");
                    break;

            }

            Log.log("当前爬取 " + area.toString() + " 首字母 " + letter.toString());

            for (String s: list) {
                Log.log("爬取url:" + s);
                mapList.addAll(getSingers(s, area, letter));
            }

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
            Thread.sleep(1000); //至关重要 不能删
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("singer_list_txt")));
            elements = this.driver.findElements(By.className("singer_list_txt__item"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("url("+area.toString() +" "+ letter.toString() +")打开异常:" + url);
            return mapList;
        }

        for (int i=0; i<elements.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                name = elements.get(i).findElement(By.tagName("a")).getAttribute("title");
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

        try {
            WebElement next_page = this.driver.findElement(By.className("next"));
            new_url = url.replaceFirst("page=" + (Integer.parseInt(next_page.getAttribute("data-index"))-1),
                    "page="+Integer.parseInt(next_page.getAttribute("data-index")));
            Log.log("当前爬取 " + area.toString() + " 首字母 " + letter.toString());
            Log.log("爬取url:" + new_url);
            mapList.addAll(getSingers(new_url, area, letter));
        } catch (Exception e){
            Log.log("当前已经是"+area.toString()+" " + letter.toString() + " 最后一页" );
        }

        return mapList;
    }


    public void quit(){
        this.driver.quit();
    }


}
