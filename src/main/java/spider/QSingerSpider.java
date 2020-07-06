package spider;

import database.Db;
import database.DbDML;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QSingerSpider {
    private WebDriver driver;
    private WebDriverWait wait;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private PrintStream exceptionHandler;


    public QSingerSpider(Browser browser) throws FileNotFoundException {
        switch (browser.ordinal()){
            case 0:
                this.driver = new InternetExplorerDriver();
                break;
            case 1:
                this.driver = new SafariDriver();
                break;
            case 2:
                this.driver = new ChromeDriver();
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

    public void spider(Area area, Letter letter){
        try {

            String url = "https://y.qq.com/portal/singer_list.html#genre=-100&";
            List<String> list = new ArrayList<String>();
            switch (area.number()){
                case 11:
                    list.add(url + "sex=0&area=200&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    list.add(url + "sex=0&area=2&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 12:
                    list.add(url + "sex=1&area=200&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    list.add(url + "sex=1&area=2&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 13:
                    list.add(url + "sex=2&area=200&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    list.add(url + "sex=2&area=2&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 21:
                    list.add(url + "sex=0&area=5&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 22:
                    list.add(url + "sex=1&area=5&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 23:
                    list.add(url + "sex=2&area=5&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 31:
                    list.add(url + "sex=0&area=4&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 32:
                    list.add(url + "sex=1&area=4&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 33:
                    list.add(url + "sex=2&area=4&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 41:
                    list.add(url + "sex=0&area=3&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 42:
                    list.add(url + "sex=1&area=3&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 43:
                    list.add(url + "sex=2&area=3&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 51:
                    list.add(url + "sex=0&area=6&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 52:
                    list.add(url + "sex=1&area=6&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;
                case 53:
                    list.add(url + "sex=2&area=6&page=1&"+ "index=" + Integer.toString(letter.ordinal()+1) + "&");
                    break;

            }


            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 当前爬取 " + area.toString() + " 首字母 " + letter.toString());


            for (String s: list) {
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 爬取url:" + s);
                getSingers(s, area, letter);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void getSingers(String url, Area area, Letter letter) throws InterruptedException {
        String name;
        String singer;
        String photo;
        String new_url = " ";
        List<WebElement> elements;

        try {
            this.driver.get(url);
            Thread.sleep(5000);
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("singer_list_txt")));
            elements = this.driver.findElements(By.className("singer_list_txt__item"));
        }
        catch (Exception e){
            e.printStackTrace();
            this.exceptionHandler.println(
                    df.format(new Date())+" " + Thread.currentThread().toString() + " url("+area.toString() +" "+ letter.toString() +")打开异常:" + url);
            return;
        }





        Db db = new Db();
        Connection connection = db.openDatabase();

        for (int i=0; i<elements.size(); i++){
            //重置
            try {
                this.driver.get(url);
                this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("singer_list_txt")));
                elements = this.driver.findElements(By.className("singer_list_txt__item"));
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date())+" " + Thread.currentThread().toString() + " url("+area.toString() +" "+ letter.toString() +")打开异常:" + url + "(" + i);
                continue;
            }

            try {
                name = elements.get(i).getText();
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌手名:" +name);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌手名获取失败"+area.toString() +" "+ letter.toString()+" "+i);
                continue;
            }

            try {
                singer = elements.get(i).findElement(By.tagName("a")).getAttribute("href");
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌手url:"+ singer);
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌手url获取失败"+area.toString() +" "+ letter.toString()+" "+name);
                continue;
            }

            try {
                this.driver.get(elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
                photo = this.driver.findElement(By.className("data__photo")).getAttribute("src");
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 歌手img_url:"+ photo);

                getPhoto(this.driver.findElement(By.className("data__photo")).getAttribute("src"), name+".png");
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date())+" " + Thread.currentThread().toString() + " 歌手照片获取失败"+area.toString() +" "+ letter.toString()+" "+name);
                continue;
            }

            try {
                DbDML.executeSqlScript(connection,
                        "INSERT INTO singers(name,type,first_letter,photograph, url) VALUES (\"" +
                                name + "\"," + area.number() + ",\"" +
                                letter.toString() +
                                "\",\"src/main/resources/singer_photo/"+ name+".png\",\""+
                                singer + "\");");
                System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " "+ name +"数据插入成功");
            }
            catch (Exception e){
                e.printStackTrace();
                this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 数据插入失败"+area.toString() +" "+ letter.toString()+" "+name);
                continue;
            }

            Thread.sleep(1500);
        }

        db.closeDatabase(connection);

        try {
            this.driver.get(url);
            Thread.sleep(5000);
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className("singer_list_txt")));
        }
        catch (Exception e){
            e.printStackTrace();
            this.exceptionHandler.println(
                    df.format(new Date())+" " + Thread.currentThread().toString() + " url("+area.toString() +" "+ letter.toString() +")打开异常:" + url);
            return;
        }


        try {
            WebElement next_page = this.driver.findElement(By.className("next"));
            new_url = url.replaceFirst("page=" + (Integer.parseInt(next_page.getAttribute("data-index"))-1),
                    "page="+Integer.parseInt(next_page.getAttribute("data-index")));
            System.out.println(df.format(new Date())+" " + Thread.currentThread().toString() + " 当前爬取 " + area.toString() + " 首字母 " + letter.toString());
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 爬取url:" + new_url);
            getSingers(new_url, area, letter);
        }
        catch (Exception e){
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 当前已经是"+area.toString()+" " + letter.toString() + " 最后一页" );
        }
    }

    public void getPhoto(String url, String fileName){
        try {
            URL uri = new URL(url);
            InputStream in = uri.openStream();
            FileOutputStream fo = new FileOutputStream(new File("src/main/resources/singer_photo/"+fileName));//文件输出流
            byte[] buf = new byte[1024];
            int length = 0;
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 开始下载:" + url);
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            //关闭流
            in.close();
            fo.close();
            System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " " + fileName + "下载完成, 路径:" + "src/main/resources/singer_photo/"+fileName);
        }
        catch (IOException e){
            e.printStackTrace();
            this.exceptionHandler.println(df.format(new Date())+" " + Thread.currentThread().toString() + " 文件下载异常: " + fileName);
        }

    }

    public void quit(){
        this.driver.quit();
    }


}
