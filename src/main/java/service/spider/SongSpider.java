package service.spider;

import enumItem.Browser;
import utils.Logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SongSpider extends Thread{

    private Browser browser;
    private PrintStream exceptionHandler;
    private int start;
    private int end;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public SongSpider(Browser browser, int start, int end) throws FileNotFoundException {
        this.browser = browser;
        this.exceptionHandler = new Logger("deverror.txt").getStream();
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            QSongSpider songSpider = new QSongSpider(this.browser);
            for (int i=this.start; i<this.end; i++){
                try {
                    songSpider.spider(i);
                    System.out.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " " + i+"号歌手10首歌曲导入完成");
                }
                catch (Exception e){
                    e.printStackTrace();
                    this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " " + i+"号歌手10首歌曲导入失败");
                    continue;
                }
                Thread.currentThread().sleep(3000);
            }

            songSpider.getDriver().quit();
        }
        catch (Exception e){
            e.printStackTrace();
            this.exceptionHandler.println(df.format(new Date()) +" " + Thread.currentThread().toString()+ " " + "爬虫创建失败");
        }


    }
}
