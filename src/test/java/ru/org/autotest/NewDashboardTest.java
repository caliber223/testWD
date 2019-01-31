package ru.org.autotest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NewDashboardTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/azotov/work/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.84/");
    }

    @Test
    public void changeDashboard() {
        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = new String();
        String password = new String();
        login = "mao@mao.mao";
        password = "123";

        WebElement loginField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                                                           +"/div/ng-component/div/div/form/div[1]/div/input"));
        if(login != null && !login.isEmpty()) {
            loginField.sendKeys(login);
        }
        System.out.println("___________________ login - OK");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div/"
                                                                    +"ng-component/div/div/form/div[2]/div/input"));
        if(password != null && !password.isEmpty()) {
            passwordField.sendKeys(password);
        }
        System.out.println("___________________ password - OK");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                                                          +"/div/ng-component/div/div/form/div[3]/div/button"));
        loginButton.click();
        System.out.println("___________________ loginButton - OK");

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        mainPage.click();
        System.out.println("___________________ mainPage - OK");

        WebElement dashboardButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                                                                +"/div/div[1]/div[1]/span"));
        dashboardButton.click();
        System.out.println("___________________ dashboardButton - OK");

        WebElement addGroupButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                                                                  +"/ng-component/div/div/button"));
        addGroupButton.click();
        System.out.println("___________________ addGroupButton - OK");

        WebElement groupNameField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                                                                +"/ng-component/div/div/div[2]/div[2]/input[1]"));
        String groupName = new String();
        groupName = "testGroup(" + cTime + ")";
        groupNameField.sendKeys(groupName);
        System.out.println("___________________ groupNameField - OK");

        WebElement addWidgetButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                                                                +"/ng-component/div/div/div[2]/div[2]/button[1]"));
        addWidgetButton.click();
        System.out.println("___________________ addWidgetButton - OK");

        WebElement saveGroupButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                                                                 +"/ng-component/div/div/div[2]/div[2]/div[2]/i"));
        saveGroupButton.click();
        System.out.println("___________________ saveGroupButton - OK");


    }

    @AfterClass
    public static void tearDown() {
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch(Exception e) {
            System.out.println("Exception!");
        }
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                                                               +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }

}
