package ru.org.autotest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.*;
import java.util.ArrayList;
import java.lang.System;
import org.apache.log4j.Logger;


public class NewDashboardTest {
    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(NewDashboardTest.class);
    final private long delayMilliSec = 200;
    final private long delaySec = 1;
    private static ArrayList<String> auth = new ArrayList<>();

    private static void mSleep (long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }
        catch(Exception e) {
            log.error("Exception!");
        }
    }

    private static void milliSleep (long msec) {
        try {
            TimeUnit.MILLISECONDS.sleep(msec);
        }
        catch(Exception e) {
            log.error("Exception!");
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

        log.info("");
        log.info("");
        log.info("____________________ DASHBOARD TEST START ____________________");

        try {
            WebElement loginField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[1]/div/input"));
            if (login != null && !login.isEmpty()) {
                loginField.sendKeys(login);
            }
        } catch (Exception e) {
            log.error("Element loginField not found!");
        }

        try {
            WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div/"
                    + "ng-component/div/div/form/div[2]/div/input"));
            if (password != null && !password.isEmpty()) {
                passwordField.sendKeys(password);
            }
        } catch (Exception e) {
            log.error("Element passwordField not found!");
        }

        try {
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[3]/div/button"));
            loginButton.click();
        } catch (Exception e) {
            log.error("Element loginButton not found!");
        }

        try {
            WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
            mainPage.click();
        } catch (Exception e) {
            log.error("Element mainPage not found!");
        }

        try {
            WebElement dashboardButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                    + "/div/div[1]/div[1]/span"));
            dashboardButton.click();
        } catch (Exception e) {
            log.error("Element dashboardButton not found!");
        }

        try {
            List<WebElement> widgetsList = driver.findElements(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div"));
            log.debug("widgetList size = " + widgetsList.size());
        } catch (Exception e) {
            log.error("Element widgetList not found!");
        }

        try {
            WebElement addGroupButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/button"));
            addGroupButton.click();
        } catch (Exception e) {
            log.error("Element addGroupButton not found!");
        }
        milliSleep(delayMilliSec);

        try {
            WebElement groupNameField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[2]/div[2]/input[1]"));
            String groupName = new String();
            groupName = "autoTestGroup(" + cTime + ")";
            groupNameField.sendKeys(groupName);
        } catch (Exception e) {
            log.error("Element groupNameField not found");
        }
        milliSleep(delayMilliSec);

        try {
            WebElement saveGroupButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[2]/div[2]/div[2]"));
            saveGroupButton.click();
        } catch (Exception e) {
            log.error("Element saveGroupButton not found!");
        }

    }

    @AfterClass
    public static void tearDown() {
        mSleep(2);
        try {
            WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div/"
                    + "div[5]/div[4]"));
            logoutButton.click();
        } catch (Exception e) {
            log.error("Element logoutButton not found!");
        }

        driver.quit();
    }

}
