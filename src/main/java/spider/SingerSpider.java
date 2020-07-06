package spider;

import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SingerSpider extends  Thread {

    private Browser browser;
    private Area area;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public SingerSpider(Browser browser, Area area) {
        this.browser = browser;
        this.area = area;
    }

    @Override
    public void run() {
        try {
            QSingerSpider singerSpider = new QSingerSpider(this.browser);

            for (Letter letter: Letter.values()) {
                singerSpider.spider(this.area, letter);
                Thread.sleep(60000);
                System.out.println(df.format(new Date()) + " "+ this.area + " " + letter + " 导入完成");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
