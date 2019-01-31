package ru.org.autotest;

import java.util.Date;
import java.io.File;
import java.util.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Vector;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateAndRunSuiteTest {

    private static WebDriver driver;
    private static Vector<String> suiteNameList = new Vector();
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
    public void userLogin() {
        System.out.println("============================ CREATE SUITES ==========================");
        int numberSuites = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== CREATE SUITE ====================================================

        WebElement loginField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[1]/div/input"));
        if(login != null && !login.isEmpty()) {
            loginField.sendKeys(login);
        }
        System.out.println("___________________ login - OK");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[2]/div/input"));
        if(password != null && !password.isEmpty()) {
            passwordField.sendKeys(password);
        }
        System.out.println("___________________ password - OK");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                +"/div/ng-component/div/div/form/div[3]/div/button"));
        loginButton.click();
        System.out.println("___________________ loginButton - OK");
        milliSleep(delayMilliSec);

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        WebElement suiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]"
                + "/div[2]/div/div[1]/div[3]/span"));

        for(int n = 0; n < numberSuites; n++) {

            mainPage.click();
            System.out.println("___________________ mainPage - OK");

            suiteButton.click();
            System.out.println("___________________ suiteButton - OK");

            WebElement createSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/div/div/list-view-toolbar-share/list-view-toolbar-complex/"
                    + "list-view-toolbar/div/div/div[4]/button/span"));
            createSuiteButton.click();
            System.out.println("___________________ createSuiteButton - OK");

            WebElement createNameSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/div/div/div[1]/div[1]/div[1]/div/input"));
            String nameSuite = new String();
            nameSuite = "autoTestSuite_" + n + "_(" + cTime + ")";
            if (nameSuite != null && !nameSuite.isEmpty()) {
                createNameSuiteButton.sendKeys(nameSuite);
            }
            System.out.println("___________________ nameSuite - OK");

            WebElement projectListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/div/div/div[1]/div[2]/div[2]/div"));
            projectListButton.click();
            System.out.println("___________________ projectListButton - OK");

            WebElement projectSelectButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div/div/list-view/atr-project[15]/div/md-checkbox/div[1]"));
            projectSelectButton.click();
            System.out.println("___________________ projectSelectButton - OK");

            WebElement projectSelectApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[3]"
                    + "/div/div[1]/button"));
            projectSelectApplyButton.click();
            System.out.println("___________________ projectSelectApplyButton - OK");

            WebElement saveNewSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/div[3]/div/div/div[2]/div/button[1]"));
            saveNewSuiteButton.click();
            System.out.println("___________________ saveNewSuiteButton - OK");

            suiteNameList.add(nameSuite);

            mSleep(2);
        }

        System.out.println("============================ RUN SUITES ==========================");
        //================================== RUN CREATED SUITES =========================================
        if(suiteNameList.size() > 0) {
            for(int i = 0; i < suiteNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //   mainPage
                System.out.println("___________________ mainPage_2 - OK");

                suiteButton.click();
                System.out.println("___________________ suiteButton_2 - OK");

                WebElement desiredSuite = driver.findElement(By.linkText(suiteNameList.get(i)));
                desiredSuite.click();
                System.out.println("___________________ desiredSuite - OK");

                WebElement saveAndRunSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                                                       +"/ng-component/div/div/div[3]/div/div/div[2]/div/button[3]"));
                saveAndRunSuiteButton.click();
                System.out.println("___________________ saveAndRunSuiteButton - OK");

            }
        }
        //==================================================================================================

    }
/*
    @AfterClass
    public static void tearDown() {
        try {
            TimeUnit.SECONDS.sleep(10);
        }
        catch(Exception e) {
            System.out.println("Exception!");
        }
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }*/
}


