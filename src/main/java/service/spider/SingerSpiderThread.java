package service.spider;

import enumItem.*;
import org.openqa.selenium.WebDriver;
import service.OpenWebDriver;
import utils.Log;
import utils.Logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingerSpiderThread implements Runnable {

    private Browser browser;
    private Area area;
    private Platform platform;

    public SingerSpiderThread(Browser browser, Area area, Platform platform){
        this.browser = browser;
        this.area = area;
        this.platform = platform;
    }

    public void run() {
        try {
            WebDriver driver = new OpenWebDriver(this.browser, false).getDriver();
            SingerSpider singerSpider = null;
            switch (platform.ordinal()){
                case 0:
                    singerSpider = new QSingerSpider(driver);
                    break;
                case 1:
                    singerSpider = new WSingerSpider(driver);
                    break;
                case 2:
                    singerSpider = new KSingerSpider(driver);
                    break;
            }


            for (Letter letter: Letter.values()) {
                try {
                    singerSpider.spider(this.area, letter);
                    Thread.sleep(30000);
                    Log.log(this.area + " " + letter + " 导入完成");
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.error("爬取异常:" + area.toString() + " " + letter.toString());
                    continue;
                }
            }
            singerSpider.quit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
