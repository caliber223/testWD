package ru.org.autotest;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class CreateAndRunSuiteTest {

    private static WebDriver driver;
    private static Vector<String> suiteNameList = new Vector();

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/azotov/work/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.84/");
    }

    @Test
    public void userLogin() {
        System.out.println("============================ CREATE SUITES ==========================");
        int numberSuites = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = new String();
        String password = new String();
        login = "mao@mao.mao";
        password = "123";

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

            try {
                TimeUnit.SECONDS.sleep(3);
            }
            catch(Exception e) {
                System.out.println("Exception!");
            }
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


