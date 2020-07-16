package service.spider;

import dao.database.SingersTableActions;
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

public class KSingerSpider implements SingerSpider {

    private WebDriver driver;
    private WebDriverWait wait;

    public KSingerSpider(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    public ArrayList<HashMap<String, String>> spider(Area area, Letter letter){
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
        try {
            String url = "https://www.kugou.com/yy/singer/index/";
            switch (area.number()){
                case 11:
                    url = url + "1-" + letter.toString().toLowerCase() + "-2.html";
                    break;
                case 12:
                    url = url + "1-" + letter.toString().toLowerCase() + "-3.html";
                    break;
                case 13:
                    url = url + "1-" + letter.toString().toLowerCase() + "-4.html";
                    break;
                case 21:
                    url = url + "1-" + letter.toString().toLowerCase() + "-8.html";
                    break;
                case 22:
                    url = url + "1-" + letter.toString().toLowerCase() + "-9.html";
                    break;
                case 23:
                    url = url + "1-" + letter.toString().toLowerCase() + "10-.html";
                    break;
                case 31:
                    url = url + "1-" + letter.toString().toLowerCase() + "5-.html";
                    break;
                case 32:
                    url = url + "1-" + letter.toString().toLowerCase() + "6-.html";
                    break;
                case 33:
                    url = url + "1-" + letter.toString().toLowerCase() + "7-.html";
                    break;
                case 53:
                    url = url + "1-" + letter.toString().toLowerCase() + "-11.html";
                    break;
                default:
                    final String string = "无效的爬取请求,KuGou" + area.toString() + letter.toString();
                    Log.log(string);
                    Log.error(string);
                    return null;
            }
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
        List<WebElement> elements1;
        List<WebElement> elements2;
        ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();

        /**
         * 打开url 等待js加载
         */
        try {
            this.driver.get(url);
            Thread.sleep(1000); //至关重要 不能删
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("clear_fix")));
            elements1 = this.driver.findElements(By.className("pic"));
            elements2 = this.driver.findElements(By.className("list1"));
        } catch (Exception e){
            e.printStackTrace();
            Log.error("url("+area.toString() +" "+ letter.toString() +")打开异常:" + url);
            return mapList;
        }

        for (int i=0; i<elements1.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                name = elements1.get(i).getAttribute("title");
                map.put("name", name);
                Log.log("歌手名:" + name);
            } catch (Exception e){
                e.printStackTrace();
                Log.error("歌手名获取失败"+area.toString() +" "+ letter.toString()+" "+i);
                continue;
            }

            try {
                singer_url = elements1.get(i).getAttribute("href");
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

        for (int i=0; i<elements2.size(); i++){
            for (WebElement item:elements2.get(i).findElements(By.tagName("li"))) {
                HashMap<String, String> map = new HashMap<String, String>();
                try {
                    name = item.findElement(By.tagName("a")).getAttribute("title");
                    map.put("name", name);
                    Log.log("歌手名:" + name);
                } catch (Exception e){
                    e.printStackTrace();
                    Log.error("歌手名获取失败"+area.toString() +" "+ letter.toString()+" "+i);
                    continue;
                }

                try {
                    singer_url = item.findElement(By.tagName("a")).getAttribute("href");
                    map.put("url", singer_url);
                    Log.log("歌手url:"+ singer_url);
                } catch (Exception e){
                    e.printStackTrace();
                    Log.error("歌手url获取失败"+area.toString() +" "+ letter.toString()+" "+name);
                    continue;
                }

                SingersTableActions.singerInsert(name, area, letter, singer_url, 2);

                mapList.add(map);
            }

        }

        try {
            new_url = this.driver.findElement(By.className("NextPageSpan")).findElement(By.tagName("a")).getAttribute("href");
            if(!new_url.equals(url+"#")){
                Log.log("当前爬取 " + area.toString() + " 首字母 " + letter.toString());
                Log.log("爬取url:" + new_url);
                mapList.addAll(getSingers(new_url, area, letter));
            }else {
                Log.log("当前已经是"+area.toString()+" " + letter.toString() + " 最后一页" );
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return mapList;
    }


    public void quit(){
        this.driver.quit();
    }


}
