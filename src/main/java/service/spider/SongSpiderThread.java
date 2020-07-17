package service.spider;

import enumItem.Browser;
import enumItem.Platform;
import org.openqa.selenium.WebDriver;
import service.OpenWebDriver;
import service.spider.kugoumusic.KSongSpider;
import service.spider.qqmusic.QSongSpider;
import service.spider.wangyiyunmusic.WSongSpider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongSpiderThread implements Runnable{

    private Browser browser;
    private Platform platform;
    private List<HashMap<String,String>> list;

    public SongSpiderThread(Browser browser, Platform platform, List<HashMap<String,String>> list){
        this.browser = browser;
        this.list = list;
        this.platform = platform;
    }

    public void run() {
        try {
            WebDriver driver = new OpenWebDriver(this.browser, false).getDriver();
            SongSpider songSpider = null;
            switch (platform.ordinal()){
                case 0:
                    songSpider = new QSongSpider(driver);
                    break;
                case 1:
                    songSpider = new WSongSpider(driver);
                    break;
                case 2:
                    songSpider = new KSongSpider(driver);
                    break;
            }
            for (Map<String, String> item :
                    this.list) {
                songSpider.getSongByUrl(item.get("url"), Integer.parseInt(item.get("id")));
            }
            songSpider.quit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
