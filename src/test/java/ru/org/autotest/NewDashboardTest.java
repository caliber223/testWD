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
import java.io.File;
import java.util.*;
import java.util.ArrayList;


public class NewDashboardTest {
    private static WebDriver driver;
    final private long delayMilliSec = 200;
    final private long delaySec = 1;
    private static ArrayList<String> auth = new ArrayList<>();

    private static void mSleep (long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }
        catch(Exception e) {
            System.out.println("Exception!");
        }
    }

    private static void milliSleep (long msec) {
        try {
            TimeUnit.MILLISECONDS.sleep(msec);
        }
        catch(Exception e) {
            System.out.println("Exception!");
        }
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/azotov/work/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Scanner sc = new Scanner(new File("/home/azotov/IdeaProjects/t.txt"));
            String strLine;
            int s = 0;
            while(sc.hasNext()) {
                strLine = sc.nextLine();
                auth.add(s, strLine);
                s++;
            }
        }
        catch (Exception e) {
            System.out.println("Error! File not found");
        }

        driver.get(auth.get(0));
    }

    @Test
    public void changeDashboard() {
        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

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
        milliSleep(delayMilliSec);

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
        mSleep(2);
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                                                               +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }

}
