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

public class CreateDeleteAssertionsTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(CreateDeleteAssertionsTest.class);
    private static Vector<String> conditionNameList = new Vector();
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
        } catch (Exception e) {
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
        log.info("\n\n============================ CREATE ASSERTION ==========================");
        int numberConditions = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== CREATE ASSERTION ====================================================

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
        WebElement libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                +"/div/div[1]/div[5]/span"));

        WebElement assertionsButton = null;

        for(int n = 0; n < numberConditions; n++) {

            mainPage.click();
            log.info("___________________ mainPage - OK");

            libraryButton.click();
            log.info("___________________ libraryButton - OK");

            assertionsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                    +"/div[2]/div[3]/span"));
            assertionsButton.click();
            assertionsButton.click();
            log.info("___________________ assertionButton - OK");

            WebElement createAssertionButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/div/div/list-view-toolbar-share/"
                    +"list-view-toolbar-complex/list-view-toolbar/div/div/div[4]/button/span"));
            createAssertionButton.click();

            WebElement createNameConditionButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    +"/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[1]/div/input"));
            String nameCondition = new String();
            nameCondition = "autoTestCondition_" + n + "_(" + cTime + ")";
            if (nameCondition != null && !nameCondition.isEmpty()) {
                createNameConditionButton.sendKeys(nameCondition);
            }
            log.info("___________________ nameCondition - OK");

            WebElement expressionWindow = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre"));
            expressionWindow.click();
            log.info("___________________ expressionWindow - OK");

            WebElement expressionsField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/codemirror/div/div[1]/textarea"));
            String expression = "ANY load.actions.succeeds + load.actions.fails + load.actions.aborts "
                    +"== load.actions.attempts";
            milliSleep(delayMilliSec);
            expressionsField.sendKeys(expression);
            log.info("___________________ expressionsField - OK");

            WebElement showSyntaxHelpButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[1]/div[1]"));
            showSyntaxHelpButton.click();
            log.info("___________________ showSyntaxHelpButton - OK");

            WebElement showSyntaxHelp2Button = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[1]/div[1]"));
            showSyntaxHelp2Button.click();
            log.info("___________________ showSyntaxHelpButton - OK");

            WebElement lineWrappingButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[2]/div[1]"));
            lineWrappingButton.click();
            log.info("___________________ lineWrappingButton - OK");

            WebElement lineWrapping2Button = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[2]/div[1]"));
            lineWrapping2Button.click();
            log.info("___________________ lineWrapping2Button - OK");

            WebElement lineNumbersButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[3]/div[1]"));
            lineNumbersButton.click();
            lineNumbersButton.click();
            log.info("___________________ lineNumberButton - OK");

            WebElement createAssertionApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    +"/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[3]"
                    +"/div/div[1]/button"));
            createAssertionApplyButton.click();
            log.info("___________________ createAssertionApplyButton - OK");

            conditionNameList.add(nameCondition);
            log.info("___________________ condition " + nameCondition + " created successful");

            mSleep(2);
        }

        log.info("============================ DELETE ASSERTIONS ==========================");
        //================================== DELETE CREATED ASSERTIONS =========================================
        if(conditionNameList.size() > 0) {
            for(int i = 0; i < conditionNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //   mainPage
                log.info("___________________ mainPage_2 - OK");

                libraryButton.click();
                log.info("___________________ libraryButton_2 - OK");

                driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                        +"/div[2]/div[3]/span"));
                assertionsButton.click();
                log.info("___________________ assertionsButton_2 - OK");

                List<WebElement> conditions = driver.findElements(By.tagName("atr-condition"));
                log.debug("___________________ conditionList size = " + conditions.size());

                for (int j = 0; j < conditions.size(); j++) {
                    log.debug("....... iteration " + j + "  step 1");
                    WebElement cName = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                            +"ng-component/div/ng-component/div/div/div/div/list-view/"
                            +"atr-condition[" + (j + 1) + "]/div/div[3]/div/div[1]/div"));
                    log.debug("....... iteration " + j + "  step 2");
                    if (conditionNameList.get(i).equals(cName.getText())) {
                        log.debug("....... iteration " + j + "  step 3");
                        log.info("Condition " + conditionNameList.get(i) + " is found");
                        WebElement conditionDeleteButton = driver.findElement(By.xpath("/html/body/app-root/div/"
                                +"div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                +"atr-condition[" + (j + 1) + "]/div/div[3]/div/div[5]/button[2]"));
                        conditionDeleteButton.click();
                        log.debug("....... iteration " + j + "  step 4");
                        milliSleep(delayMilliSec);

                        WebElement contextDeleteButton = driver.findElement(By.xpath("/html/body/app-root/"
                                +"context-menu/ul/li[8]"));
                        contextDeleteButton.click();
                        milliSleep(delayMilliSec);

                        WebElement deleteApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/"
                                +"div[2]/ng-component/div/dialogs-list/div/div/"
                                +"content-dialog/div/div[1]/div/div[3]/button"));
                        deleteApplyButton.click();
                        log.debug("....... iteration " + j + "  step 5");
                        milliSleep(delayMilliSec);
                        log.info("Condition " + conditionNameList.get(i) + " is deleted successfully");
                        break;
                    }
                }

            }
        }
        //==================================================================================================

    }

    @AfterClass
    public static void tearDown() {
        milliSleep(200);
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/"
                +"div/div[5]/div[4]"));
        logoutButton.click();
        driver.quit();
    }
}



