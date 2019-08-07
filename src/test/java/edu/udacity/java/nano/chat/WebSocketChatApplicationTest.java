package edu.udacity.java.nano.chat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import edu.udacity.java.nano.WebSocketChatApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebSocketChatApplication.class)
public class WebSocketChatApplicationTest {

	WebDriver webDriver;
	SeleniumConfig config;
	
	@LocalServerPort
    private int port;

	@Test
    public void loginAndJoin() throws Exception {
		config = new SeleniumConfig();
		webDriver = config.getWebDriver();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		
        webDriver.get("http://localhost:"+ port);
        String title = config.getWebDriver().getTitle();
        Assert.assertEquals("Chat Room Login", title);
        
        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys("myself");

        WebElement login_form = webDriver.findElement(By.className("submit"));
        login_form.click();


        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement username1 = webDriver.findElement(By.id("username"));
                System.out.println("username: " + username1.getText());
                return username1.getText().equals("myself");
            }
        });
        
        webDriver.close();
	}
	@Test
    public void chat() throws Exception {
		config = new SeleniumConfig();
		webDriver = config.getWebDriver();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		
        webDriver.get("http://localhost:"+ port);
        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys("myself");

        WebElement login_form = webDriver.findElement(By.className("submit"));
        login_form.click();
        
        webDriver.findElement(By.id("msg")).sendKeys("My name is who?");
        WebElement sendButton = webDriver.findElement(By.className("sendMessage"));
        sendButton.click();
        Thread.sleep(2000);
        WebElement messageContainer = webDriver.findElement(By.className("message-container"));
        WebElement messageContent = messageContainer.findElement(By.className("message-content"));
        Assert.assertEquals("myself:My name is who?", messageContent.getText());
        
       
        webDriver.close();
    
    }
	
	@Test
    public void leave() throws Exception {
		config = new SeleniumConfig();
		webDriver = config.getWebDriver();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		
        webDriver.get("http://localhost:"+ port);
        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys("myself");

        WebElement login_form = webDriver.findElement(By.className("submit"));
        login_form.click();
        
        webDriver.findElement(By.id("msg")).sendKeys("My name is who?");
        WebElement logoutElement = webDriver.findElement(By.id("logout"));
        logoutElement.click();

        Assert.assertEquals("Chat Room Login", config.getWebDriver().getTitle());
       
        webDriver.close();
    
    }

}
