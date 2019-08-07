package edu.udacity.java.nano.chat;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {
    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SeleniumConfig() {
        Capabilities capabilities = new ChromeOptions();
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    static {
        System.setProperty("webdriver.chrome.driver", findFile("chromedriver.exe"));
    }

    static private String findFile(String filename) {
        String paths[] = {"", "bin/", "target/classes"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }

}