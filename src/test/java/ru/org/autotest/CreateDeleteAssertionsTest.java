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

public class CreateDeleteAssertionsTest {

    private static WebDriver driver;
    private static Vector<String> conditionNameList = new Vector();
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
        } catch (Exception e) {
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
        System.out.println("============================ CREATE ASSERTION ==========================");
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
        WebElement libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                +"/div/div[1]/div[5]/span"));

        WebElement assertionsButton = null;

        for(int n = 0; n < numberConditions; n++) {

            mainPage.click();
            System.out.println("___________________ mainPage - OK");

            libraryButton.click();
            System.out.println("___________________ libraryButton - OK");

            assertionsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                    +"/div[2]/div[3]/span"));
            assertionsButton.click();
            assertionsButton.click();
            System.out.println("___________________ assertionButton - OK");

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
            System.out.println("___________________ nameCondition - OK");

            WebElement expressionWindow = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]"));
            expressionWindow.click();
            System.out.println("___________________ expressionWindow - OK");

            WebElement expressionsField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div[1]/pre/span"));
            String expression = "ANY load.actions.succeeds + load.actions.fails + load.actions.aborts == load.actions.attempts";
            expressionsField.click();
            expressionsField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div[1]/pre/span"));
            expressionsField.sendKeys(expression);
          /*  WebElement keyAny = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span/span[1]"));
            keyAny.sendKeys("ANY");

            WebElement key2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span/span[2]"));
            key2.sendKeys("load");
            WebElement key3 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span/span[3]"));
            key3.sendKeys("actions");
            WebElement key4 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span/span[4]"));
            key4.sendKeys("attempts");*/
            System.out.println("___________________ expressionsField - OK");

            /*WebElement showSyntaxHelpButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[1]/div[1]"));
            showSyntaxHelpButton.click();
            System.out.println("___________________ showSyntaxHelpButton - OK");

            WebElement showSyntaxHelp2Button = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[1]/div[1]"));
            showSyntaxHelp2Button.click();
            System.out.println("___________________ showSyntaxHelpButton - OK");

            WebElement lineWrappingButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[2]/div[1]"));
            lineWrappingButton.click();
            System.out.println("___________________ lineWrappingButton - OK");

            WebElement lineWrapping2Button = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[2]/div[1]"));
            lineWrapping2Button.click();
            System.out.println("___________________ lineWrapping2Button - OK");

            WebElement lineNumbersButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                    +"/div[1]/md-checkbox[3]/div[1]"));
            lineNumbersButton.click();
            lineNumbersButton.click();
            System.out.println("___________________ lineNumberButton - OK");*/

            WebElement createAssertionApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    +"/div[2]/ng-component/div/ng-component/dialog-condition/dm-dialog/div/div/div/div[3]"
                    +"/div/div[1]/button"));
            createAssertionApplyButton.click();
            System.out.println("___________________ createAssertionApplyButton - OK");

            conditionNameList.add(nameCondition);

            mSleep(2);
        }

        System.out.println("============================ DELETE SUITES ==========================");
        //================================== DELETE CREATED SUITES =========================================
        if(conditionNameList.size() > 0) {
            for(int i = 0; i < conditionNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //   mainPage
                System.out.println("___________________ mainPage_2 - OK");

                libraryButton.click();
                System.out.println("___________________ libraryButton_2 - OK");

                driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                        +"/div[2]/div[3]/span"));
                assertionsButton.click();
                System.out.println("___________________ assertionsButton_2 - OK");

                WebElement desiredCondition = driver.findElement(By.linkText(conditionNameList.get(i)));
                desiredCondition.click();
                System.out.println("___________________ desiredCondition - OK");

                WebElement deleteConditionButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"+
                        "/div[2]/ng-component/div/ng-component/div/div/div/div/list-view"));
                deleteConditionButton.click();
                System.out.println("___________________ deleteConditionButton - OK");

                WebElement deleteConditionApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                        +"/div[2]/ng-component/div/dialogs-list/div/div/content-dialog/div/div[1]/div/div[3]/button"));
                deleteConditionApplyButton.click();

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



