import database.Db;
import database.DbDDL;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import enumItem.Table;
import spider.SingerSpider;

import java.sql.Connection;


public class main {
    public static void main(String[] args) throws InterruptedException {

        Db db = new Db();

        Connection connection = db.openDatabase();

        DbDDL.tableDelete(connection, Table.singers);
        DbDDL.tableCreate(connection, Table.singers);


        db.closeDatabase(connection);

        SingerSpider singerSpider = new SingerSpider(Browser.CHROME);

        singerSpider.spider(Area.JPN_M, Letter.H);

    }
}
