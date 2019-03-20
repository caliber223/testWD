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
        boolean testState = true;
        log.info("\n");
        log.info("________________________________________________________________________________________");
        log.info("======================================= TEST START =====================================");
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
            testState = false;
            log.error("Element loginField not found!");
        }

        try {
            WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[2]/div/input"));
            if (password != null && !password.isEmpty()) {
                passwordField.sendKeys(password);
            }
        } catch (Exception e) {
            testState = false;
            log.error("Element passwordField not found!");
        }

        try {
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[3]/div/button"));
            loginButton.click();
        } catch (Exception e) {
            testState = false;
            log.error("Element loginButton not found!");
        }
        log.info("Authorization was successful");

        milliSleep(delayMilliSec);

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        WebElement pages;

        for(int n = 0; n < numberPage; n++) {
            try {
             //   mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
                mainPage.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element mainPage not found!");
            }

            //---------------------------- ADD NEW PAGE -----------------------------------------
            try {
                pages = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]"));
                pages.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element pages not found!");
            }

            try {
              //  WebElement addPageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[4]/span"));
              //  driver.find_element_by_xpath("//div[contains(text(),'Add User')]");
              //  driver.find_element_by_xpath("//button[contains(text(),'Add User')]");
              //  driver.findElement(By.Xpath("//strong[contains(text(),'" + service +"')]"));
                WebElement addPageButton = driver.findElement(By.xpath("//*[contains(text(), 'Add Page')]"));
                addPageButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element addPageButton not found!");
            }

            try {
                WebElement editPageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/div/i"));
                editPageButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element editPageButton not found!");
            }

            String nameDashboardPage = "";
            WebElement namePageField;
            try {
                namePageField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/input"));
                nameDashboardPage = "autoTestDasboardPage_" + n + "_(" + cTime + ")";
                if (nameDashboardPage != null && !nameDashboardPage.isEmpty()) {
                    namePageField.clear();
                    namePageField.sendKeys(nameDashboardPage);
                }
            } catch (Exception e) {
                testState = false;
                log.error("Element namePageField not found!");
            }

            try {
                WebElement selectIconButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/"
                        +"div[1]/div[1]/i"));
                selectIconButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element selectIconButton not found!");
            }

            try {
                WebElement iconList = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]"));
                milliSleep(200);
            } catch (Exception e) {
                testState = false;
                log.error("Element iconList not found!");
            }

            try {
                WebElement selectedIcon = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[26]/i"));
                selectedIcon.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element selectedIcon not found!");
            }
            milliSleep(200);

            try {
                WebElement selectColorButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/div[2]"));
                selectColorButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element selectColorButton not found!");
            }

            try {
                WebElement colorList = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]"));
                milliSleep(200);
            } catch (Exception e) {
                testState = false;
                log.error("Element colorList not found!");
            }

            try {
                WebElement selectedColor = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[28]"));
                selectedColor.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element selectedColor not found!");
            }

            WebElement addNewWidgetButton;
            try {
                addNewWidgetButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/button[1]"));
                addNewWidgetButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element addNewWidgetButton not found!");
            }

            try {
                WebElement commonStats = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[1]"));
                commonStats.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element commonStats not found!");
            }
            mSleep(1);

            try {
                addNewWidgetButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/button[1]"));
                addNewWidgetButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element addNewWidgetButton not found!");
            }

            try {
                WebElement systemStats = driver.findElement(By.xpath("/html/body/app-root/div[3]/div/div[2]/div/div[2]"));
                systemStats.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element systemStats not found!");
            }
            mSleep(1);

            //---------------------------- Add appliance -------------------------------------------------------------

           /* try {
                addNewWidgetButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/button[1]"));
                addNewWidgetButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element addNewWidgetButton not found!");
            }*/







            //---------------------------------------- Save All -----------------------------------------------------------

            try {
                WebElement savePageButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        +"div/div/div[1]/div[3]/button[1]"));
                savePageButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element savePageButton not found!");
            }

            pageNameList.add(nameDashboardPage);
            log.info("Page creation was successful");
            mSleep(1);
        }
        mSleep(2);

        //------------------------------------- DELETE NEW PAGE -------------------------------------------
        for (int d = 0; d < pageNameList.size(); ++d) {
            try {
                mainPage.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element mainPage not found!");
            }

            try {
                pages = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]"));
                pages.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element pages not found!");
            }

          //  WebElement onePage;
            try {
                WebElement onePage = driver.findElement(By.xpath("//span[contains(text(),'" + pageNameList.get(d) +"')]"));
                onePage.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element onePage for delete not found!");
                continue;
            }

            try {
                WebElement editButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/div/i"));
                editButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element editButton (delete) not found!");
            }

            try {
                WebElement deleteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/div/div[1]/button[2]"));
                deleteButton.click();
            } catch (Exception e) {
                testState = false;
                log.error("Element deleteButton ");
            }

            try {
                WebElement deleteButtonApply = driver.findElement(By.xpath("/html/body/app-root/dialogs-list/div/div/content-dialog/div/"
                        +"div[1]/div/div[3]/button"));
                milliSleep(200);
                deleteButtonApply.click();
                log.info("Dashboard page " + pageNameList.get(d) + " has been deleted successfully!");
            } catch (Exception e) {
                testState = false;
                log.error("Element deleteButtonApply not found!");
            }

        }

        if(testState) {
            log.info("Test passed");
        } else {
            log.info("Test failed");
        }
        log.info("===================================== END OF TEST =====================================");
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



