package ru.org.autotest;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
import java.util.concurrent.TimeUnit;

public class CreateDeleteChartTest {

    private static WebDriver driver;
    private static Vector<String> newChartNameList = new Vector();

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
        System.out.println("============================ CREATE CHART ==========================");
        int numberCharts = 1;   //-------------------- !!!!!!!!--------------------

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = new String();
        String password = new String();
        login = "mao@mao.mao";
        password = "123";

        //================================== CREATE CHART ====================================================

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
        WebElement libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                +"/div/div[1]/div[4]/span"));

        WebElement chartsButton = null;

        for(int n = 0; n < numberCharts; n++) {

            mainPage.click();
            System.out.println("___________________ mainPage - OK");

            libraryButton.click();
            System.out.println("___________________ libraryButton - OK");

            chartsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[4]/span"));
            chartsButton.click();
            chartsButton.click();
            System.out.println("___________________ chartsButton - OK");

         //   WebElement createChartButtonArea = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"));
         //   createChartButtonArea.click();

            WebElement createChartButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/div/div/list-view-toolbar-share/list-view-toolbar-complex/"
                    +"list-view-toolbar/div/div/div[4]/button/span"));
            createChartButton.click();
            System.out.println("___________________ createChartButton - OK");

            WebElement createNameChartButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[1]/div/input"));
            String nameChart = new String();
            nameChart = "autoTestChart_" + n + "_(" + cTime + ")";
            if (nameChart != null && !nameChart.isEmpty()) {
                createNameChartButton.sendKeys(nameChart);
            }
            System.out.println("___________________ nameChart - OK");

            WebElement shortDescriptionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[1]/input"));
            shortDescriptionField.click();
            shortDescriptionField.sendKeys("some description");
            System.out.println("___________________ shortDescription - OK");

            WebElement typeDataField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[2]/select"));
            typeDataField.click();
            System.out.println("___________________ typeDataField - OK");

            WebElement typeData = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[2]/select/option[1]"));
            typeData.click();
            System.out.println("___________________ typeData - OK");

            WebElement expressionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div/codemirror/div/div[6]/div[1]/div/div/div/div[5]/div/pre"));
            expressionField.click();
            expressionField.click();
            System.out.println("___________________ expressionField - OK");

            WebElement editorContainer = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div"));           // container
            editorContainer.click();
            System.out.println("___________________ editorConteiner - OK");

            WebElement expressionText = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[2]/div/codemirror/div/div[1]/textarea"));  //  textarea
            expressionText.sendKeys("load.actions.attempts");
            System.out.println("___________________ expressionText - OK");

            shortDescriptionField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[2]/div/div[1]/div[2]/div[1]/input"));
            shortDescriptionField.click();

//=============================================  ADD NEW GRAPH  =====================================================

            WebElement addGraphButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]"));
            addGraphButton.click();
            System.out.println("___________________ addGraphButton - OK");

            WebElement shortDescription2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[1]/input"));
            shortDescription2Field.click();
            shortDescription2Field.sendKeys("some description 2");
            System.out.println("___________________ shortDescription2Field - OK");

            WebElement typeData2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[2]/select"));
            typeData2Field.click();
            System.out.println("___________________ typeData2Field - OK");

            WebElement typeData2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[1]/div[2]/div[2]/select/option[6]"));
            typeData2.click();
            System.out.println("___________________ typeData2 - OK");

            WebElement expression2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]"));
            expression2Field.click();
            expression2Field.click();
            System.out.println("___________________ expression2Field - OK");

            WebElement editorContainer2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]/div"));           // container
            editorContainer2.click();
            System.out.println("___________________ editorContainer2 - OK");

            WebElement expressionText2 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    +"/div[2]/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div[3]/div/div[2]/div/codemirror/div/div[1]/textarea"));  //  textarea
            expressionText2.sendKeys("http.tx.packets");
            System.out.println("___________________ expressionText2 - OK");

// ==================================================================================================================

            WebElement createChartApplyButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-chart-template/dm-dialog/div/div/div/div[3]"
                    +"/div/div[1]/button"));
            createChartApplyButton.click();
            System.out.println("___________________ createChartApplyButton - OK");

            newChartNameList.add(nameChart);

        }

        System.out.println("============================ DELETE CHARTS ==========================");
        //================================== DELETE CREATED CHARTS =========================================
        if(newChartNameList.size() > 0) {
            for(int i = 0; i < newChartNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //   mainPage
                System.out.println("___________________ mainPage fo delete - OK");

                libraryButton.click();
                System.out.println("___________________ libraryButton for delete - OK");

                driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[4]/span"));
                chartsButton.click();
                System.out.println("___________________ chartsButton for delete - OK");

                List<WebElement> listCharts =  (List<WebElement>) driver.findElements(By.tagName("atr-chart-template"));
                System.out.printf("___________________ listCharts for delete - OK, number charts: %d\n", listCharts.size());

                WebElement oneChart = null;

                for(int k = 0; k < listCharts.size(); k++) {
                    System.out.printf("___________________ it is entry ...  iteration = %d\n", k);
                    WebElement temp = listCharts.get(k);

                    try {
                        oneChart = temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                                +"div/ng-component/div/div/div/div/list-view/atr-chart-template[" + (k+1) + "]/div"
                                +"/div[3]/div[1]/div[1]/div"));
                 //       /html/body/app-root/div/div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/atr-chart-template[1]/div/div[3]/div[1]/div[1]/div/text()
                        String atrib = oneChart.getText();
                        System.out.printf("- - - - - - - - %s\n", atrib);
                        if(atrib.equals(newChartNameList.get(i))) {
                            System.out.println("___________________ This element is exist!!!");
                            temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                                    +"div/ng-component/div/div/div/div/list-view/atr-chart-template[" + (k+1) + "]"));

                            WebElement chartActionsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                                    +"/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                    +"atr-chart-template[" + (k+1) + "]/div/div[3]/div[1]/div[5]/button[2]"));

                            TimeUnit.SECONDS.sleep(10);
                            chartActionsButton.click();

                            System.out.println("___________________ chartActionsButton - OK");
                            break;
                        }
                    }
                    catch(Exception e) {
                        System.out.println(">>>>>>>>>>>>>>   Exception! Element not found   <<<<<<<<<<<<<<<<");
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
            System.out.println("Exception!");
        }
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]"
                +"/div/div[5]/div[3]/span"));
        logoutButton.click();
        driver.quit();
    }*/
}




