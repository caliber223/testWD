package ru.org.autotest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateUserTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/azotov/work/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.84/");
    }

    @Test
    public void CreateUser() {
        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = new String();
        String password = new String();
        login = "mao@mao.mao";
        password = "123";

        //========================== CREATE USER =======================================

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
        mainPage.click();
        System.out.println("___________________ mainPage - OK");

        WebElement adminButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div/div[1]/div[8]/span"));
        adminButton.click();
        System.out.println("___________________ adminButton - OK");

        WebElement userListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[2]/span"));
        userListButton.click();
        System.out.println("___________________ userListButton - OK");

        WebElement addUserButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar/"
                +"div/div/div[5]/button/span"));
        addUserButton.click();
        System.out.println("___________________ addUserButton - OK");

        WebElement emailUserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[1]/div/input"));
        String emailUser = new String();
        emailUser = "autotest2@autotest.autotest";
        if(emailUser != null && !emailUser.isEmpty()) {
            emailUserField.sendKeys(emailUser);
        }
        System.out.println("___________________ emailUserField - OK");

        WebElement nameUserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[2]/div/input"));
        String nameUser = new String();
        nameUser = "testUser(" + cTime + ")";
        if(nameUser != null && !nameUser.isEmpty()) {
            nameUserField.sendKeys(nameUser);
        }
        System.out.println("___________________ nameUserField - OK");

        WebElement timeZoneField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[1]/div/div[3]/div/select"));
        String timeZone = new String();
        timeZone = "UTC";
        if(timeZone != null && !timeZone.isEmpty()) {
            timeZoneField.sendKeys(timeZone);
        }
        System.out.println("___________________ timeZoneField - OK");

        String userPassword = new String();
        userPassword = "123";
        WebElement password1UserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]"
                +"/div/div/div[2]/div/div/input[1]"));
        if(userPassword != null && !userPassword.isEmpty()) {
            password1UserField.sendKeys(userPassword);
        }
        System.out.println("___________________ password1UserField - OK");

        WebElement password2UserField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[2]/div/div/div[2]"
                +"/div/div/input[2]"));
        if(userPassword != null &&!userPassword.isEmpty()) {
            password2UserField.sendKeys(userPassword);
        }
        System.out.println("___________________ password2UserField - OK");

        WebElement createUser = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                +"/ng-component/div/ng-component/dialog-user-profile/dm-dialog/div/div/div/div[3]"
                +"/div/div[1]/button"));
        createUser.click();
        System.out.println("___________________ createUserField - OK");


    }

    @AfterClass
    public static void tearDown() {
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch(Exception e) {
            System.out.println("Exception!");
        }
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                                                               +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }

}
