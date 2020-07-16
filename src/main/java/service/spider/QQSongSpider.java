package service.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class QQSongSpider {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://y.qq.com/portal/search.html#page=1&searchid=1&remoteplace=txt.yqq.top&t=playlist&w=love%20story").timeout(1000).get();
        System.out.println(document.getElementsByClass("songlist__list").text());
    }






}