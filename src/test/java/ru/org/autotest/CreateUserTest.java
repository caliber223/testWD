package ru.org.autotest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class CreateUserTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(CreateUserTest.class);
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
            log.error("Error! File not found");
        }

        driver.get(auth.get(0));
    }

    @Test
    public void CreateUser() {
        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //========================== CREATE USER =======================================
        log.info("\n\n========================== CREATE USER =======================================");

        WebElement loginField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[1]/div/input"));
        if(login != null && !login.isEmpty()) {
            loginField.sendKeys(login);
        }
        log.info("___________________ login - OK");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[2]/div/input"));
        if(password != null && !password.isEmpty()) {
            passwordField.sendKeys(password);
        }
        log.info("___________________ password - OK");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[3]/div/button"));
        loginButton.click();
        log.info("___________________ loginButton - OK");
        milliSleep(delayMilliSec);

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        mainPage.click();
        log.info("___________________ mainPage - OK");

        WebElement adminButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div/div[1]/div[8]/span"));
        adminButton.click();
        log.info("___________________ adminButton - OK");

        WebElement userListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[2]/span"));
        userListButton.click();
        log.info("___________________ userListButton - OK");

        WebElement addUserButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar/"
                +"div/div/div[5]/button/span"));
        addUserButton.click();
        log.info("___________________ addUserButton - OK");

        WebElement emailUserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[1]/div/input"));
        String emailUser = new String();
        emailUser = "autotest3@autotest.autotest";
        if(emailUser != null && !emailUser.isEmpty()) {
            emailUserField.sendKeys(emailUser);
        }
        log.info("___________________ emailUserField - OK");

        WebElement nameUserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[2]/div/input"));
        String nameUser = new String();
        nameUser = "testUser(" + cTime + ")";
        if(nameUser != null && !nameUser.isEmpty()) {
            nameUserField.sendKeys(nameUser);
        }
        log.info("___________________ nameUserField - OK");

        WebElement timeZoneField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[3]/div/select"));
        String timeZone = new String();
        timeZone = "UTC";
        if(timeZone != null && !timeZone.isEmpty()) {
            timeZoneField.sendKeys(timeZone);
        }
        log.info("___________________ timeZoneField - OK");

        String userPassword = new String();
        userPassword = "123";
        WebElement password1UserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[2]/div/div/input[1]"));
        if(userPassword != null && !userPassword.isEmpty()) {
            password1UserField.sendKeys(userPassword);
        }
        log.info("___________________ password1UserField - OK");

        WebElement password2UserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                +"/div/div/input[2]"));
        if(userPassword != null &&!userPassword.isEmpty()) {
            password2UserField.sendKeys(userPassword);
        }
        log.info("___________________ password2UserField - OK");

        WebElement createUser = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[3]"
                +"/div/div[1]/button"));
        createUser.click();
        log.info("___________________ createUserField - OK");

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
