import database.Db;
import database.DbDDL;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import enumItem.Table;
import spider.QSingerSpider;
import spider.SingerSpider;
import utils.MultiOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;


public class main {
    public static void main(String[] args){

        try {
            String fileName = "log1.txt";

            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/log/" + fileName, true);
            PrintStream out = new PrintStream(fileOutputStream);



            MultiOutputStream outputStream = new MultiOutputStream(out, System.out);
            System.setOut(new PrintStream(outputStream));

            Db db = new Db();

            Connection connection = db.openDatabase();

            DbDDL.tableDelete(connection, Table.singers);
            DbDDL.tableCreate(connection, Table.singers);

            db.closeDatabase(connection);

            new SingerSpider(Browser.CHROME, Area.CHN_M).start();
            new SingerSpider(Browser.CHROME, Area.CHN_F).start();
            new SingerSpider(Browser.CHROME, Area.CHN_G).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
