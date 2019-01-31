package ru.org.autotest;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class CreateDeleteSuitesTest {

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
        int numberSuites = 2;

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

            WebElement note = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/div/div/div[1]/div[1]/div[2]/div/textarea"));
            note.clear();
            note.sendKeys("Note for " + nameSuite);
            System.out.println("___________________ new Note - OK");

            WebElement lifeTime = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/div/div/div[1]/div[1]/div[3]/div/input"));
            lifeTime.clear();
            lifeTime.sendKeys("12");
            System.out.println("___________________ lifeTime - OK");

            //---------------------------------- ADD NEW TAGS ----------------------------------------------------

            WebElement tagsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/div/div/div[1]/div[1]/div[4]/div/button"));
            tagsButton.click();
            System.out.println("___________________ tagsButton - OK");

            WebElement newRootTagButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[1]/button[1]"));
            newRootTagButton.click();
            System.out.println("___________________ newRootTagButton - OK");

            WebElement newTagSpace = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div"));
            newTagSpace.click();
            System.out.println("___________________ newTagSpace - OK");

            WebElement setColorTag = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/"
                    +"div[2]/div[1]/div/div"));
            setColorTag.click();
            System.out.println("___________________ setColor - OK");

            WebElement yellowColor = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[3]/"
                    +"div/div[80]"));
            yellowColor.click();
            System.out.println("___________________ yellowColor - OK");

            WebElement addIconButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div/div[2]/div[2]/div/button"));
            addIconButton.click();
            System.out.println("___________________ addIconButton - OK");

            WebElement newIcon = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[3]/"
                    +"div/div[35]/i"));
            newIcon.click();
            System.out.println("___________________ newIcon - OK");

            WebElement tagName = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[2]/div/div/div/div[2]/"
                    +"div[3]/div/input"));
            tagName.sendKeys("autoTestTag_" + n + "_(" + cTime + ")");

            WebElement addTagApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-tags-select/dialog-tag/dm-dialog/div/div/div/div[3]/"
                    +"div/div[1]/button"));
            addTagApplyButton.click();
            System.out.println("___________________ addTagApplyButton - OK");

            WebElement selectTag = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"tree-view/div[3]/div[2]/md-checkbox"));
            selectTag.click();
            System.out.println("___________________ selectTag - OK");

            WebElement newTagApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-tags-select/dm-dialog/div/div/div/div[3]/div/div[1]/button"));
            newTagApplyButton.click();
            System.out.println("___________________ newTagApplyButton - OK");
            //------------------------------------- STOP ADD TAGS---------------------------------------------------

            //------------------------------------- ADD NEW  RUN PARAMETERS ----------------------------------------
/*
            WebElement newRunParametersButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/div/div/div[1]/div[3]/div[1]/button[4]/i"));
            newRunParametersButton.click();
            System.out.println("___________________ projectListButton - OK");

            WebElement runParamTotalArea = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div"));
            runParamTotalArea.click();
            System.out.println("___________________ runParamTotalArea - OK");

            WebElement newRunParamName = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div/div/div[2]/"
                    +"div/div/div/div/div[1]/div/input"));
            newRunParamName.sendKeys("autoTestRunParam_" + n + "_(" + cTime + ")");
            System.out.println("___________________ runParamTotalArea - OK");

            WebElement newRunParamSaveButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/div/div[3]/dialog-suite-params/dm-dialog/div/div/div/div[3]/"
                    +"div/div[1]/button[2]"));
            newRunParamSaveButton.click();
            System.out.println("___________________ runParamTotalArea - OK");
*/
            //------------------------------------------------------------------------------------------------------

            WebElement projectListButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/div/div/div[1]/div[2]/div[2]/div"));
            projectListButton.click();
            System.out.println("___________________ projectListButton - OK");

            WebElement projectSelectButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    + "ng-component/div/div/div[3]/dialog-select-projects/dm-dialog/div/div/div/div[2]"
                    + "/div/div/div/div/list-view/atr-project[4]/div/md-checkbox/div[1]"));
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

        System.out.println("============================ DELETE SUITES ==========================");
        //================================== DELETE CREATED SUITES =========================================
        if(suiteNameList.size() > 0) {
            for(int i = 0; i < suiteNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //  mainPage
                System.out.println("___________________ mainPage_2 - OK");

                suiteButton.click();
                System.out.println("___________________ suiteButton_2 - OK");

                WebElement desiredSuite = driver.findElement(By.linkText(suiteNameList.get(i)));
                desiredSuite.click();
                System.out.println("___________________ desiredSuite - OK");

                WebElement deleteSuiteButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/div/div[3]/div/div/div[2]/button[3]"));
                deleteSuiteButton.click();
                System.out.println("___________________ deleteSuiteButton - OK");

                WebElement deleteSuiteApply = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        +"/ng-component/dialogs-list[1]/div/div/content-dialog/div/div[1]/div/div[3]/button"));
                deleteSuiteApply.click();
                System.out.println("___________________ deleteSuiteApply - OK");
            }
        }
        //==================================================================================================
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        System.out.println("___________________ main page 2 - OK");


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
