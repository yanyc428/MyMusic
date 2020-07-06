package spider;

import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import utils.Logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingerSpider extends  Thread {

    private Browser browser;
    private Area area;
    private PrintStream exceptionHandler;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    public SingerSpider(Browser browser, Area area) throws FileNotFoundException {
        this.browser = browser;
        this.area = area;
        this.exceptionHandler = new Logger("deverror.txt").getStream();
    }

    @Override
    public void run() {
        try {
            QSingerSpider singerSpider = new QSingerSpider(this.browser);


            for (Letter letter: Letter.values()) {
                try {
                    singerSpider.spider(this.area, letter);
                    Thread.sleep(60000);
                    System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " "+ this.area + " " + letter + " 导入完成");
                }
                catch (Exception e){
                    e.printStackTrace();
                    this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " 爬取异常:" + area.toString() + " " + letter.toString());
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
