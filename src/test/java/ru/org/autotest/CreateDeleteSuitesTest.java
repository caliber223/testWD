package ru.org.autotest;

import java.util.Date;
import java.io.File;
import java.util.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Vector;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class CreateDeleteSuitesTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(CreateDeleteSuitesTest.class);
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
        int numberSuites = 2;

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
        } catch(Exception e) {
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
            milliSleep(delayMilliSec);
        } catch (Exception e) {
            log.error("Element loginButton not found!");
        }

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
                log.error("Element createSueteDutton");
            }

            String nameSuite = "autoTestSuite_" + n + "_(" + cTime + ")";
            try {
                WebElement createNameSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/div/div/div[1]/div[1]/div[1]/div/input"));
                nameSuite = "autoTestSuite_" + n + "_(" + cTime + ")";
                if (nameSuite != null && !nameSuite.isEmpty()) {
                    createNameSuiteButton.sendKeys(nameSuite);
                }
            } catch (Exception e) {
                    log.error("Element createNameSuiteButton");
            }

            try  {WebElement note = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/div/div/div[1]/div[1]/div[2]/div/textarea"));
                note.clear();
                 note.sendKeys("Note for " + nameSuite);
                 } catch (Exception e) {
                log.error("Element note not found");
            }

            try {
                WebElement lifeTime = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/div/div/div[1]/div[1]/div[3]/div/input"));
                lifeTime.clear();
                lifeTime.sendKeys("12");
            } catch (Exception e) {
                log.info("Element lifeTime not found!");
            }

            //---------------------------------- ADD NEW TAGS ----------------------------------------------------

            try {
                WebElement tagsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/div/div/div[1]/div[1]/div[4]/div/button"));
                tagsButton.click();
            } catch (Exception e) {
                log.error("Element tagsButton not found!");
            }

            try {
                WebElement newRootTagButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[1]/button[1]"));
                newRootTagButton.click();
            } catch (Exception e) {
                log.error("Element newRootTagButton not found!");
            }

            try {
                WebElement newTagSpace = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div"));
                newTagSpace.click();
            } catch (Exception e) {
                log.error("Element newTagSpace not found!");
            }

            try {
                WebElement setColorTag = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/"
                        + "div[2]/div[1]/div/div"));
                setColorTag.click();
            } catch (Exception e) {
                log.error("Element setColorTag not found!");
            }

            try {
                WebElement yellowColor = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[3]/"
                        + "div/div[80]"));
                yellowColor.click();
            } catch (Exception e) {
                log.error("Element yellowColor not found!");
            }

            try {
                WebElement addIconButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]"
                        + "/div/div/div/div[2]/div[2]/div/button"));
                addIconButton.click();
            } catch (Exception e) {
                log.error("Element addIconButton not found!");
            }

            try {
                WebElement newIcon = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[3]/"
                        + "div/div[35]/i"));
                newIcon.click();
            } catch (Exception e) {
                log.error("Element newIcon not found!");
            }

            try {WebElement tagName = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[2]/"
                    +"div[3]/div/input"));
            tagName.sendKeys("autoTestTag_" + n + "_(" + cTime + ")");
            } catch (Exception e) {
                log.error("Element tagName not found!");
            }

            try {
                WebElement addTagApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[3]/"
                        + "div/div[1]/button"));
                addTagApplyButton.click();
            } catch (Exception e) {
                log.error("Element addTagApplyButton not found!");
            }

            try {
                WebElement selectTag = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                        + "tree-view/div[3]/div[2]/md-checkbox"));
                selectTag.click();
            } catch (Exception e) {
                log.error("Element selectTag");
            }

            try {
                WebElement newTagApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[3]/div/div[1]/button"));
                newTagApplyButton.click();
            } catch (Exception e) {
                log.error("Element newTagApplyButton not found!");
            }
            //------------------------------------- STOP ADD TAGS---------------------------------------------------

            //------------------------------------- ADD NEW  RUN PARAMETERS ----------------------------------------

            WebElement newRunParametersButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/div/div/div[1]/div[3]/div[1]/button[4]/i"));
            newRunParametersButton.click();
            log.info("___________________ projectListButton - OK");

            WebElement runParamTotalArea = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div"));
            runParamTotalArea.click();
            log.info("___________________ runParamTotalArea - OK");

            WebElement newRunParamName = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div/div/div[2]/"
                    +"div/div/div/div/div[1]/div/input"));
            newRunParamName.sendKeys("autoTestRunParam_" + n + "_(" + cTime + ")");
            log.info("___________________ runParamTotalArea - OK");

            WebElement newRunParamSaveButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div/div/div[3]/"
                    +"div/div[1]/button[2]"));
            newRunParamSaveButton.click();
            log.info("___________________ runParamTotalArea - OK");

            //------------------------------------------------------------------------------------------------------

            WebElement projectListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/div/div/div[1]/div[2]/div[2]/div"));
            projectListButton.click();
            log.info("___________________ projectListButton - OK");

            WebElement projectSelectButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[2]"
                    + "/div/div/div/div/list-view/atr-project[4]/div/md-checkbox/div[1]"));
            projectSelectButton.click();
            log.info("___________________ projectSelectButton - OK");

            WebElement projectSelectApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[3]"
                    + "/div/div[1]/button"));
            projectSelectApplyButton.click();
            log.info("___________________ projectSelectApplyButton - OK");

            WebElement saveNewSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    + "/ng-component/div/div/div[3]/div/div/div[2]/div/button[1]"));
            saveNewSuiteButton.click();
            log.info("___________________ saveNewSuiteButton - OK");

            suiteNameList.add(nameSuite);

            mSleep(2);
        }

        log.info("============================ DELETE SUITES ==========================");
        //================================== DELETE CREATED SUITES =========================================
        if(suiteNameList.size() > 0) {
            for(int i = 0; i < suiteNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //  mainPage
                log.info("___________________ mainPage_2 - OK");

                suiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]"
                                  + "/div[2]/div/div[1]/div[3]/span"));
                suiteButton.click();
                log.info("___________________ suiteButton_2 - OK");

                WebElement desiredSuite = driver.findElement(By.linkText(suiteNameList.get(i)));
                desiredSuite.click();
                log.info("___________________ desiredSuite - OK");

                WebElement deleteSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/div/div/div[2]/button[3]"));
                deleteSuiteButton.click();
                log.info("___________________ deleteSuiteButton - OK");

                WebElement deleteSuiteApply = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        +"/ng-component/dialogs-list[1]/div/div/content-dialog/div/div[1]/div/div[3]/button"));
                deleteSuiteApply.click();
                log.info("___________________ deleteSuiteApply - OK");
            }
        }
        //==================================================================================================
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        log.info("___________________ main page 2 - OK");


      //  WebElement profileUser = driver.findElement(By.cssSelector(".login-button__user"));
      //  String mailUser = profileUser.getText();
      //  Assert.assertEquals("autotestorgua@ukr.net", mailUser);
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
        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div"));
        mainPage.click();
        System.out.println("___________________ main page 3 - OK");

        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                                                              +"/div/div[5]/div[4]"));
        logoutButton.click();
        System.out.println("___________________ logoutButton click - OK");
        driver.quit();
    }*/
}

