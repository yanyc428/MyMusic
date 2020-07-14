package service.spider;

import dao.database.SingersTableActions;
import dao.database.SongsTableActions;
import enumItem.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import utils.Logger;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QSongSpider {

    private WebDriver driver;
    private WebDriverWait wait;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private PrintStream exceptionHandler;

    public QSongSpider(Browser browser) throws FileNotFoundException {
        switch (browser.ordinal()){
            case 0:
                this.driver = new InternetExplorerDriver();
                break;
            case 1:
                this.driver = new SafariDriver();
                break;
            case 2:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                this.driver = new ChromeDriver(chromeOptions);
                break;
            case 3:
                this.driver = new FirefoxDriver();
                break;
        }
        this.wait = new WebDriverWait(driver, 10);
        this.exceptionHandler = new Logger("deverror.txt").getStream();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void spider(int id){
        try {
            String url = SingersTableActions.singerSelectUrlById(id);
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 当前爬取 " + id +"号歌手");
            getSong(id, url);
        }catch (Exception e){
            e.printStackTrace();
            this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ id +"号歌手爬取失败");
        }
    }

    public void getSong(int id, String url){
        String name;
        String album;
        String new_url;
        String img_url;
        String lyric;
        String s;
        List<WebElement> elements;

        try {
            this.driver.get(url);
            Thread.sleep(5000);
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("songlist__list")));
            elements = this.driver.findElement(By.className("songlist__list")).findElements(By.tagName("li"));
        }
        catch (Exception e){
            e.printStackTrace();
            this.exceptionHandler.println(
                    df.format(new Date())+" " + Thread.currentThread().toString() + " url("+ id + ")打开异常:" + url);
            return;
        }

        for(int i=0; i<elements.size(); i++){
            try {
                this.driver.get(url);
                this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("songlist__list")));
                elements = this.driver.findElement(By.className("songlist__list")).findElements(By.tagName("li"));
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(
                        df.format(new Date())+" " +
                                Thread.currentThread().toString() +
                                " url("+ Integer.toString(id) + "," + i  + ")打开异常:" + url);
                continue;
            }

            try {
                name = elements.get(i).findElement(By.className("songlist__songname_txt")).getText();
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲名:" +name);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(
                        df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲名获取失败"+"("+id+","+i+")");
                continue;
            }

            try {
                new_url = elements.get(i).findElement(
                        By.className("songlist__songname_txt")).findElement(By.tagName("a")).getAttribute("href");
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲url:" +new_url);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲url获取失败"+"("+id+","+i+","+name+")");
                continue;
            }

            try {
                album = elements.get(i).findElement(
                        By.className("songlist__album")).getText();
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲专辑:" +album);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲专辑获取失败"+"("+id+","+i+","+name+")");
                continue;
            }



            try {
                this.driver.get(new_url);
                this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("lyric__cont_box")));
                try {
                    this.wait.until(ExpectedConditions.textToBePresentInElement(this.driver.findElement(By.className("lyric__cont_box")), " "));
                }
                catch (Exception e1){
                    try {
                        this.wait.until(ExpectedConditions.textToBePresentInElement(this.driver.findElement(By.className("lyric__cont_box")), "纯音乐"));
                    }
                    catch (Exception e2){
                        this.wait.until(ExpectedConditions.textToBePresentInElement(this.driver.findElement(By.className("lyric__cont_box")), "暂无歌词"));
                    }
                }

                lyric = this.driver.findElement(By.className("lyric__cont_box")).getAttribute("textContent").trim();
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲歌词获取成功:"+ name);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date())+" " + Thread.currentThread().toString() +
                        " 歌曲歌词获取失败"+"("+id+","+i+","+name+")");
                continue;
            }

            try {
                this.driver.get(new_url);
                img_url = this.driver.findElement(By.className("data__photo")).getAttribute("src");
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌曲img_url:"+ img_url);
                getPhoto(img_url, name, id, album, lyric);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date())+" " + Thread.currentThread().toString() + " 歌曲照片获取失败"+"("+id+","+i+","+name+")");
                continue;
            }

            try {
                SongsTableActions.songInsert(name, id, album, new_url, lyric, 0);
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " "+ name +"数据插入成功");
            }catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 数据插入失败"+ id +" "+name);
                continue;
            }
            try {
                Thread.currentThread().sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void getPhoto(String url, String name, Integer singerId, String album, String lyric){
        String fileName = "src/main/resources/song_photo/"+ singerId + album + lyric.substring(0,3) +".png";
        fileName = fileName.replaceAll(" ", "");
        try {
            URL uri = new URL(url);
            InputStream in = uri.openStream();
            FileOutputStream fo = new FileOutputStream(new File(fileName));//文件输出流
            byte[] buf = new byte[1024];
            int length = 0;
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 开始下载:" + url);
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            //关闭流
            in.close();
            fo.close();
            System.out.println(df.format(new Date()) +" " +
                    Thread.currentThread().toString()+ " " +
                    name +".png" +
                    "下载完成, 路径:" +
                    fileName);
        }
        catch (IOException e){
            e.printStackTrace();
            Log.error("文件下载异常: " + singerId + album + lyric.substring(0,3) +".png");
        }

    }
}
