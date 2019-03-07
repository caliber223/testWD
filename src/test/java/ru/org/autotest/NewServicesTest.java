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
    private static Vector<String> pageNameList = new Vector();
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
        log.info("\n\n====================================== TEST START =====================================");
        int numberPage = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);


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
        log.info("Authorization was successful");

        milliSleep(delayMilliSec);

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));

        log.info("============================ CREATE NEW OBJECTS ==========================");
        for(int n = 0; n < numberPage; n++) {
            try {
             //   mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
                mainPage.click();
            } catch (Exception e) {
                log.error("Element mainPage not found!");
            }

            //---------------------------- ADD NEW PAGE -----------------------------------------
            try {
                WebElement newDashboardPage = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[3]/span"));
                newDashboardPage.click();
            } catch (Exception e) {
                log.error("Element newDashboardPage not found");
            }

            try {
                WebElement addPageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[2]/span"));
                addPageButton.click();
            } catch (Exception e) {
                log.error("Element addPageButton not found!");
            }

            try {
                WebElement editPageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/div/i"));
                editPageButton.click();
            } catch (Exception e) {
                log.error("Element editPageButton not found!");
            }

            String nameDashboardPage = "";
            try {
                WebElement namePageField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/input"));
                nameDashboardPage = "autoTestDasboardPage_" + n + "_(" + cTime + ")";
                if (nameDashboardPage != null && !nameDashboardPage.isEmpty()) {
                    namePageField.clear();
                    namePageField.sendKeys(nameDashboardPage);
                }
            } catch (Exception e) {
                log.error("Element namePageField not found!");
            }

            try {
                WebElement selectIconButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/"
                        +"div[1]/div[1]/i"));
                selectIconButton.click();
            } catch (Exception e) {
                log.error("Element selectIconButton not found!");
            }

            try {
                WebElement iconList = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]"));
            } catch (Exception e) {
                log.error("Element iconList not found!");
            }

            try {
                WebElement selectedIcon = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[26]/i"));
                selectedIcon.click();
            } catch (Exception e) {
                log.error("Element selectedIcon not found!");
            }

            try {
                WebElement selectColorButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/div[2]"));
                selectColorButton.click();
            } catch (Exception e) {
                log.error("Element selectColorButton not found!");
            }

            try {
                WebElement colorList = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]"));
            } catch (Exception e) {
                log.error("Element colorList not found!");
            }

            try {
                WebElement selectedColor = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[28]"));
                selectedColor.click();
            } catch (Exception e) {
                log.error("Element selectedColor not found!");
            }




            try {
                WebElement savePageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/div[3]/button[1]"));
                savePageButton.click();
            } catch (Exception e) {
                log.error("Element savePageButton not found!");
            }

            pageNameList.add(nameDashboardPage);
            log.info("Page creation was successful");
            mSleep(2);
        }

        //------------------------------------- DELETE NEW PAGE -------------------------------------------
        for (int d = 0; d < numberPage; ++d) {
            try {
                mainPage.click();
            } catch (Exception e) {
                log.error("Element mainPage not found!");
            }
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



