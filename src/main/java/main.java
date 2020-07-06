import database.Db;
import database.DbDDL;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import enumItem.Table;
import spider.QSingerSpider;
import spider.SingerSpider;
import utils.Logger;
import utils.MultiOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class main {
    public static void main(String[] args){

        try {
            String fileName = "devlog.txt";


            PrintStream out = new Logger(fileName).getStream();
//
//            MultiOutputStream outputStream = new MultiOutputStream(out, System.out);
//            System.setOut(new PrintStream(outputStream));



//            new SingerSpider(Browser.CHROME, Area.CHN_M).start();
//            new SingerSpider(Browser.CHROME, Area.CHN_F).start();
//            new SingerSpider(Browser.CHROME, Area.CHN_G).start();

            List<Area> list = new ArrayList<Area>();
            for (Area area: Area.values()) {
                if (area.equals(Area.CHN_F) || area.equals(Area.CHN_G) || area.equals(Area.CHN_M)){
                    continue;
                }
                list.add(area);
            }

            for (Area area: list) {
                System.out.println(area);
//                new SingerSpider(Browser.CHROME, area).start();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
