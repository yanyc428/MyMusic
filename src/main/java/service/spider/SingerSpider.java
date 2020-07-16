package service.spider;

import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.WebDriver;
import service.OpenWebDriver;
import utils.Log;
import utils.Logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingerSpider extends  Thread {

    private Browser browser;
    private Area area;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public SingerSpider(Browser browser, Area area){
        this.browser = browser;
        this.area = area;
    }

    @Override
    public void run() {
        try {
            WebDriver driver = new OpenWebDriver(this.browser, false).getDriver();
            QSingerSpider singerSpider = new QSingerSpider(driver);

            for (Letter letter: Letter.values()) {
                try {
                    singerSpider.spider(this.area, letter);
                    Thread.sleep(50000);
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
