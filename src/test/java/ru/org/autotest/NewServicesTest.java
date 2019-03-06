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
import org.apache.log4j.Logger;

public class NewServicesTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(NewServicesTest.class);
    private static Vector<String> suiteNameList = new Vector();
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
    public void userLogin() {
        log.info("\n\n============================ CREATE SUITES ==========================");
        int numberSuites = 1;   //-------------------- !!!!!!!!--------------------


        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== CREATE SUITE ====================================================

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
            WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[2]/div/input"));
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
        milliSleep(delayMilliSec);

        WebElement mainPage;
        WebElement suiteButton;

        for(int n = 0; n < numberSuites; n++) {
            try {
                mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
                mainPage.click();
            } catch (Exception e) {
                log.error("Element mainPage not found!");
            }

            try {
                suiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]"
                        + "/div[2]/div/div[1]/div[3]/span"));
                suiteButton.click();
            } catch (Exception e) {
                log.error("Element suiteButton not found!");
            }

            try {
                WebElement createSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div/div/list-view-toolbar-share/list-view-toolbar-complex/"
                        + "list-view-toolbar/div/div/div[4]/button/span"));
                createSuiteButton.click();
            } catch (Exception e) {
                log.error("Element createSuiteButton not found!");
            }

            String nameSuite = "";
            try {
                WebElement createNameSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/div/div/div[1]/div[1]/div[1]/div/input"));
                nameSuite = "autoTestSuite_" + n + "_(" + cTime + ")";
                if (nameSuite != null && !nameSuite.isEmpty()) {
                    createNameSuiteButton.sendKeys(nameSuite);
                }
            } catch (Exception e) {
                log.error("Element createNameSuiteButton not found!");
            }

            try {
                WebElement projectListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/div/div/div[1]/div[2]/div[2]/div"));
                projectListButton.click();
            } catch (Exception e) {
                log.error("Element projectListButton not found!");
            }

            try {
                WebElement projectSelectButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[2]"
                        + "/div/div/div/div/list-view/atr-project[15]/div/md-checkbox/div[1]"));
                projectSelectButton.click();
            } catch (Exception e) {
                log.error("Element projectSelectButton not found!");
            }

            try {
                WebElement projectSelectApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[3]"
                        + "/div/div[1]/button"));
                projectSelectApplyButton.click();
            } catch (Exception e) {
                log.error("Element projectSelectApplyButton not found!");
            }

            try {
                WebElement saveNewSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/div/div/div[2]/div/button[1]"));
                saveNewSuiteButton.click();
            } catch (Exception e) {
                log.error("Element saveNewSuiteButton not found!");
            }

            suiteNameList.add(nameSuite);
            log.info("Suite [" + nameSuite + "] creation was successful");

            mSleep(2);
        }



    }
 /*   @AfterClass
    public static void tearDown() {
        mSleep(5);
        try {
            WebElement titleField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
            titleField.click();
        } catch (Exception e) {
            log.error("Element titleField not found! (tearDown())");
        }

        try {
            WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div/"
                    + "div[5]/div[4]"));
            logoutButton.click();
        } catch (Exception e) {
            log.error("Element logoutButton not found! (tearDown())");
        }

        driver.quit();
    }*/
}



