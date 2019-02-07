package ru.org.autotest;

import java.io.File;
import java.util.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateEditDeleteEventsTest {

    private static WebDriver driver;
    private static Vector<String> eventNameList = new Vector();
    final private long delayMilliSec = 200;
    final private long delaySec = 1;
    enum TypeEvent {UNDEFINED, CALENDAR, EXTERNAL}
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
        }
        catch(Exception e) {
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
        System.out.println("============================ CREATE EVENTS ==========================");
        int numberEvents = 1;
        int numberExternalEvents = 1;

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== AUTHORISATION ====================================================

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

        //==================================== ADD CALENDAR EVENT  =======================================

        WebElement mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        WebElement libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                +"/div[1]/div[1]/div[5]/span"));
        WebElement eventsButton = null;

        for(int n = 0; n < numberEvents; n++) {

            mainPage.click();
            System.out.println("___________________ mainPage - OK");

            libraryButton.click();
            System.out.println("___________________ libraryButton - OK");

            milliSleep(200);
            eventsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[5]"));
            eventsButton.click();
            System.out.println("___________________ eventsButton - OK");

            WebElement createNewCalendarEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar"
                    +"/div/div/div[4]/button[1]"));
            createNewCalendarEvent.click();
            System.out.println("___________________ createNewCalendarEventButton - OK");

            WebElement createNameEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[1]"
                    +"/div/input"));
            String nameEvent = new String();
            nameEvent = "autoTestCalendarEvent_" + n + "_(" + cTime + ")";
            if (nameEvent != null && !nameEvent.isEmpty()) {
                createNameEventButton.sendKeys(nameEvent);
            }
            System.out.println("___________________ nameEvent - OK");

            WebElement alwaysActiveButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/"
                    +"div/div/div[2]/div[1]/md-checkbox/div[1]"));
            alwaysActiveButton.click();
            milliSleep(delayMilliSec);

            WebElement startButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]"
                    +"/div[1]/div/md-checkbox/div[1]"));
            startButton.click();
            milliSleep(delayMilliSec);

            WebElement startField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/div[1]/div/input"));
            startField.click();

            WebElement monthButton = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/"
                    +"div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/"
                    +"bs-datepicker-navigation-view/button[2]/span"));
            monthButton.click();

            WebElement monthSelectJune = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                    +"div/bs-month-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[3]/span"));
            monthSelectJune.click();

            WebElement daySelect10 = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                    +"div/bs-days-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[3]/td[3]/span"));
            daySelect10.click();

            WebElement hourField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/div[1]/div/timepicker/table/tbody/tr[2]/td[1]/input"));
            hourField.sendKeys("06");

            WebElement minuteField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/div[1]/div/timepicker/table/tbody/tr[2]/td[3]/input"));
            minuteField.sendKeys("25");
            milliSleep(delayMilliSec);

            WebElement endButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/"
                    +"div/md-checkbox/div[1]"));
            endButton.click();

            WebElement endField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                    +"div[2]/div[2]/div/input"));
            endField.click();

            WebElement month2Button = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div"
                    +"/div/bs-days-calendar-view/bs-calendar-layout/div[1]/"
                    +"bs-datepicker-navigation-view/button[2]/span"));
            month2Button.click();

            WebElement month2SelectAugust = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/"
                    +"div/div/bs-month-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[3]/td[2]/span"));
            month2SelectAugust.click();

            WebElement day2Select20 = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                    +"div/bs-days-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[4]/td[5]"));
            day2Select20.click();

            WebElement hour2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/"
                    +"div/timepicker/table/tbody/tr[2]/td[1]/input"));
            hour2Field.sendKeys("11");

            WebElement minute2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/"
                    +"div[2]/div/timepicker/table/tbody/tr[2]/td[3]/input"));
            minute2Field.sendKeys("15");


            WebElement everyHour = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[1]/div[2]"));
            everyHour.click();

            WebElement triggerMinute = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[1]/input"));
            triggerMinute.sendKeys("50");   //  50 minutes

            WebElement triggerHour = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                    +"div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/trigger-edit/"
                    +"div/div[3]/div[2]/table/tbody/tr[2]/td[5]"));
            triggerHour.click();    // 10 hours

            WebElement triggerDay10 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[3]/table/tbody/tr[2]/td[2]"));
            WebElement triggerDay15 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[3]/table/tbody/tr[2]/td[7]"));
            WebElement triggerDay20 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[3]/table/tbody/tr[3]/td[4]"));
            triggerDay10.click();
            triggerDay15.click();
            triggerDay20.click();

            WebElement triggerMonthJune   = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[2]"));
            WebElement triggerMonthJule   = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[3]"));
            WebElement triggerMonthAugust = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                    +"trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[4]"));
            triggerMonthJune.click();
            triggerMonthJule.click();
            triggerMonthAugust.click();

            WebElement addAction = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[4]/"
                    +"label/button"));
            addAction.click();

            List<WebElement> actionsList = driver.findElements(By.tagName("event-action"));
            System.out.printf("\n____________________ size of actionsList = %d\n", actionsList.size());

            WebElement selectAction = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dialog-select-actions/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div/list-view/event-action[3]/atr-suite/div/md-checkbox/div[1]"));
            selectAction.click();

            WebElement addActionApply = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dialog-select-actions/dm-dialog/div/div/div/div[3]/"
                    +"div/div[1]/button"));
            addActionApply.click();

            WebElement saveNewCalendarEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[3]/div/div[1]/button"));
            saveNewCalendarEventButton.click();
            System.out.println("___________________ saveNewEventButton - OK");

            eventNameList.add(nameEvent);

            mSleep(2);
        }

        //======================================== ADD EXTERNAL EVENT ======================================

        for (int m = 0; m < numberExternalEvents; ++m) {
            mainPage.click();
            milliSleep(delayMilliSec);
            libraryButton.click();
            milliSleep(delayMilliSec);
            eventsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[5]"));
            eventsButton.click();
            milliSleep(delayMilliSec);

            WebElement createExternalEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar/div/"
                    +"div/div[4]/button[2]"));
            createExternalEvent.click();
            milliSleep(delayMilliSec);

            WebElement createNameExternalEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]/"
                    +"div/div/div/div/div[1]/div/input"));
            String nameExternalEvent = new String();
            nameExternalEvent = "autoTestExternalEvent_" + m + "_(" + cTime + ")";
            if (nameExternalEvent != null && !nameExternalEvent.isEmpty()) {
                createNameExternalEvent.sendKeys(nameExternalEvent);
            }
            milliSleep(delayMilliSec);

            WebElement templateField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]"
                    +"/div/div/div/div/div[2]/select"));
            templateField.click();
            milliSleep(delayMilliSec);

            WebElement selectTemplate = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                    +"ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]/"
                    +"div/div/div/div/div[2]/select/option[3]"));
            selectTemplate.click();
            milliSleep(delayMilliSec);

            WebElement saveExternalEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                    +"/ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[3]/"
                    +"div/div[1]/button"));
            saveExternalEventButton.click();
            milliSleep(delayMilliSec);
            System.out.println("___________________ saveNewExternalEvent - OK");
            eventNameList.add(nameExternalEvent);
            System.out.printf("\n___________________ eventNameList.size = %d\n", eventNameList.size());
            mSleep(2);
        }
        //===================================================================================================

        System.out.println("============================ DELETE EVENTS ==========================");
        //================================== DELETE CREATED EVENTS =========================================
        if(eventNameList.size() > 0) {
            TypeEvent typeEvent = TypeEvent.UNDEFINED;
            for(int i = 0; i < eventNameList.size(); i++) {
                driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //  mainPage
                System.out.println("___________________ mainPage_2 - OK");

                libraryButton.click();
                System.out.println("___________________ libraryButton_2 - OK");

                eventsButton.click();
                System.out.println("___________________ eventsButton_2 - OK");

                //============================== DELETE CALENDAR EVENTS ===================================
                List<WebElement> listEvents = driver.findElements(By.tagName("calendar-event"));
                System.out.printf("___________________ listCharts for delete - OK, number events: %d\n",
                        listEvents.size());

                WebElement oneCalendarEvent = null;

                for(int k = 0; k < listEvents.size(); ++k) {
                    System.out.printf("___________________ it is entry ...  iteration = %d\n", k);
                    WebElement temp = listEvents.get(k);

                    try {
                        oneCalendarEvent = temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                                +"ng-component/div/ng-component/div/div/div/div/list-view/"
                                +"calendar-event[" + (k + 1) + "]/div/div[3]/div[1]/div[1]/div"));

                        String atrib = oneCalendarEvent.getText();
                        System.out.printf("- - - - - - - - %s\n", atrib);
                        if(atrib.equals(eventNameList.get(i))) {
                            typeEvent = TypeEvent.CALENDAR;
                            System.out.println("___________________ This event is CALENDAR type!!!");
                            temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/"
                                    + "ng-component/div/div/div/div/list-view/calendar-event[" + (k + 1) + "]"));
                            milliSleep(delayMilliSec);

                            WebElement eventDeleteButton = driver.findElement(By.xpath("/html/body/app-root/div/"
                                    + "div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                    + "calendar-event[" + (k + 1) + "]/div/div[3]/div[1]/div[4]/button[1]"));
                            eventDeleteButton.click();
                            milliSleep(delayMilliSec);

                            WebElement eventDeleteApplyButton = driver.findElement(By.xpath("/html/body/app-root/"
                                    +"div/div[2]/div[2]/ng-component/div/dialogs-list/div/div/content-dialog/"
                                    +"div/div[1]/div/div[3]/button"));
                            eventDeleteApplyButton.click();
                            milliSleep(delayMilliSec);

                            System.out.println("___________________ calender event has been deleted successfully! -OK");
                            break;
                        }
                    }
                    catch (Exception e) {
                        System.out.println(">>>>>>>   Exception! Element not found in calendar events  <<<<<<<<<<");
                    }
                }

                //=============================== DELETE EXTERNAL EVENTS =======================================
                List<WebElement> listExternalEvents = driver.findElements(By.tagName(("external-event")));
                WebElement oneExternalEvent = null;
                for(int l = 0; l < listExternalEvents.size(); ++l) {
                    WebElement temp = listExternalEvents.get(l);

                    try {
                        oneExternalEvent = temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                                +"ng-component/div/ng-component/div/div/div/div/list-view/"
                                +"external-event[" + (l + 1) + "]/div/div[3]/div[1]/div[1]"));   //    /b
                        String atrib = oneExternalEvent.getText();
                        System.out.printf("- - - - - - - - %s\n", atrib);
                        if(atrib.equals(eventNameList.get(i))) {
                            typeEvent = TypeEvent.EXTERNAL;
                            System.out.println("___________________ This event is EXTERNAL type!!!");
                            temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/"
                                    +"ng-component/div/div/div/div/list-view/external-event[" + (l + 1) + "]"));
                            milliSleep(delayMilliSec);

                            WebElement externalEventDeleteButton = driver.findElement(By.xpath("/html/body/app-root/"
                                    +"div/div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                    +"external-event[" + (l + 1) + "]/div/div[3]/div[1]/div[4]/button[1]"));
                            externalEventDeleteButton.click();
                            milliSleep(delayMilliSec);

                            WebElement externalEventDeleteApplyButton = driver.findElement(By.xpath("/html/body/"
                                    +"app-root/div/div[2]/div[2]/ng-component/div/dialogs-list/div/div/"
                                    +"content-dialog/div/div[1]/div/div[3]/button"));
                            externalEventDeleteApplyButton.click();
                            milliSleep(delayMilliSec);

                            System.out.println("___________________ external event has been deleted successfully! -OK");
                            break;
                        }
                    }
                    catch(Exception e) {
                        System.out.println(">>>>>>>   Exception! Element not found in external events  <<<<<<<<<<");
                    }

                }

            }
        }
        //==================================================================================================
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        System.out.println("___________________ main page 2 - OK");

    }

    @AfterClass
    public static void tearDown() {
        mSleep(5);
        WebElement titleField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        titleField.click();
        System.out.println("___________________ title field for exit - OK");

        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div/"
                +"div[5]/div[4]"));
        logoutButton.click();
        System.out.println("___________________ logoutButton click - OK");
        driver.quit();
    }
}

