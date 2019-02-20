package ru.org.autotest;

import java.io.File;
import java.util.*;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class CreateDeleteChartTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(CreateDeleteChartTest.class);
    private static Vector<String> newChartNameList = new Vector();
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
        log.info("\n\n============================ CREATE CHART ==========================");
        int numberCharts = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== CREATE CHART ====================================================

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

        WebElement chartsButton = null;

        for(int n = 0; n < numberCharts; n++) {

            mainPage.click();
            log.info("___________________ mainPage - OK");

            libraryButton.click();
            log.info("___________________ libraryButton - OK");

            chartsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[4]/span"));
            chartsButton.click();
            chartsButton.click();
            log.info("___________________ chartsButton - OK");

         //   WebElement createChartButtonArea = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"));
         //   createChartButtonArea.click();

            WebElement createChartButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/div/div/list-view-toolbar-share/list-view-toolbar-complex/"
                    +"list-view-toolbar/div/div/div[4]/button/span"));
            createChartButton.click();
            log.info("___________________ createChartButton - OK");

            WebElement createNameChartButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[1]/div/input"));
            String nameChart = new String();
            nameChart = "autoTestChart_" + n + "_(" + cTime + ")";
            if (nameChart != null && !nameChart.isEmpty()) {
                createNameChartButton.sendKeys(nameChart);
            }
            log.info("___________________ nameChart - OK");

            WebElement shortDescriptionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[1]/input"));
            shortDescriptionField.click();
            shortDescriptionField.sendKeys("some description");
            log.info("___________________ shortDescription - OK");

            WebElement typeDataField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[2]/select"));
            typeDataField.click();
            log.info("___________________ typeDataField - OK");

            WebElement typeData = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[2]/select/option[1]"));
            typeData.click();
            log.info("___________________ typeData - OK");

            WebElement expressionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre"));
            expressionField.click();
            expressionField.click();
            log.info("___________________ expressionField - OK");

            WebElement editorContainer = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div"));           // container
            editorContainer.click();
            log.info("___________________ editorConteiner - OK");

            WebElement expressionText = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div/codemirror/div/div[1]/textarea"));  //  textarea
            expressionText.sendKeys("load.actions.attempts");
            log.info("___________________ expressionText - OK");

            shortDescriptionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[1]/input"));
            shortDescriptionField.click();

//=============================================  ADD NEW GRAPH  =====================================================

            WebElement addGraphButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]"));
            addGraphButton.click();
            log.info("___________________ addGraphButton - OK");

            WebElement shortDescription2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[1]/input"));
            shortDescription2Field.click();
            shortDescription2Field.sendKeys("some description 2");
            log.info("___________________ shortDescription2Field - OK");

            WebElement typeData2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[2]/select"));
            typeData2Field.click();
            log.info("___________________ typeData2Field - OK");

            WebElement typeData2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[2]/select/option[6]"));
            typeData2.click();
            log.info("___________________ typeData2 - OK");

            WebElement expression2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]"));
            expression2Field.click();
            expression2Field.click();
            log.info("___________________ expression2Field - OK");

            WebElement editorContainer2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]/div"));           // container
            editorContainer2.click();
            log.info("___________________ editorContainer2 - OK");

            WebElement expressionText2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    +"/div[2]/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]/div/codemirror/div/div[1]/textarea"));  //  textarea
            expressionText2.sendKeys("http.tx.packets");
            log.info("___________________ expressionText2 - OK");

// ==================================================================================================================

            WebElement createChartApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[3]"
                    +"/div/div[1]/button"));
            createChartApplyButton.click();
            log.info("___________________ createChartApplyButton - OK");

            newChartNameList.add(nameChart);

        }

        mSleep(delaySec);
        log.info("============================ DELETE CHARTS ==========================");
        //================================== DELETE CREATED CHARTS =========================================
        if(newChartNameList.size() > 0) {
            for(int i = 0; i < newChartNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //   mainPage
                log.info("___________________ mainPage fo delete - OK");

                libraryButton.click();
                log.info("___________________ libraryButton for delete - OK");

                driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[4]/span"));
                chartsButton.click();
                log.info("___________________ chartsButton for delete - OK");

                List<WebElement> listCharts =  (List<WebElement>) driver.findElements(By.tagName("atr-chart-template"));
                log.debug("___________________ listCharts for delete - OK, number charts: " + listCharts.size());

                WebElement oneChart = null;

                for(int k = 0; k < listCharts.size(); k++) {
                    log.debug("___________________ it is entry ...  iteration = " + k);
                    WebElement temp = listCharts.get(k);

                    try {
                        oneChart = temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                                +"div/ng-component/div/div/div/div/list-view/atr-chart-template[" + (k+1) + "]/div"
                                +"/div[3]/div[1]/div[1]/div"));
                 //       /html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/atr-chart-template[1]/div/div[3]/div[1]/div[1]/div/text()
                        String atrib = oneChart.getText();
                        log.debug("- - - - - - - - " + atrib);
                        if(atrib.equals(newChartNameList.get(i))) {
                            log.info("___________________ This element is exist!!!");
                            temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                                    +"div/ng-component/div/div/div/div/list-view/atr-chart-template[" + (k+1) + "]"));

                            WebElement chartActionsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                                    +"/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                    +"atr-chart-template[" + (k+1) + "]/div/div[3]/div[1]/div[5]/button[2]"));

                            TimeUnit.SECONDS.sleep(10);
                            chartActionsButton.click();

                            log.info("___________________ chartActionsButton - OK");
                            break;
                        }
                    }
                    catch(Exception e) {
                        log.error("Exception! Element not found");
                    }

                }

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
            log.info("Exception!");
        }
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }*/
}




