package service;

import enumItem.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class OpenWebDriver {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 构造方法
     * @param browser 默认浏览器 Chrome使用无头模式
     */
    public OpenWebDriver(Browser browser, boolean gpu) {
        switch (browser.ordinal()){
            case 0:
                this.driver = new InternetExplorerDriver();
                break;
            case 1:
                this.driver = new SafariDriver();
                break;
            case 2:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (!gpu){
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                this.driver = new ChromeDriver(chromeOptions);
                break;
            case 3:
                this.driver = new FirefoxDriver();
                break;
        }
    }

    public void getURL(String url){
        this.driver.get(url);
    }

    public void quit(){
        this.driver.quit();
    }



}
