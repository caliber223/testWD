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
import org.apache.log4j.Logger;

public class CreateEditDeleteEventsTest {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(CreateEditDeleteEventsTest.class);
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
        log.info("\n\n============================ CREATE EVENTS ==========================");
        int numberEvents = 1;
        int numberExternalEvents = 1;

        Date currentTime = new Date();
        String cTime = currentTime.toString();
        String login = auth.get(1);
        String password = auth.get(2);

        //================================== AUTHORISATION ====================================================

        try {
            WebElement loginField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[1]/div/input"));
            if (login != null && !login.isEmpty()) {
                loginField.sendKeys(login);
            }
        } catch(Exception e) {
            log.error("Element loginField not found or login == 0 or login is empty");
        }

        try {
            WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[2]/div/input"));
            if (password != null && !password.isEmpty()) {
                passwordField.sendKeys(password);
            }
        } catch(Exception e) {
            log.error("Element passwordField not found or password == 0 or password is empty");
        }

        try {
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]"
                    + "/div/ng-component/div/div/form/div[3]/div/button"));
            loginButton.click();
            log.info("Authorization was successful");
        } catch(Exception e) {
            log.error("Element loginButton not found!");
        }

        //==================================== ADD CALENDAR EVENT  =======================================

        WebElement mainPage;
        WebElement libraryButton;
        WebElement eventsButton = null;

        for(int n = 0; n < numberEvents; n++) {
            try {
                mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
                mainPage.click();
            } catch (Exception e) {
                log.error("Element mainPage not found!");
            }

            try {
                libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                        + "/div[1]/div[1]/div[5]/span"));
                libraryButton.click();
            } catch (Exception e) {
                log.error("Element libraryButton not found!");
            }

            milliSleep(200);
            try {
                eventsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[5]"));
                eventsButton.click();
            } catch(Exception e) {
                log.error("Element eventsButton not found!");
            }

            try {
                WebElement createNewCalendarEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar"
                        + "/div/div/div[4]/button[1]"));
                createNewCalendarEvent.click();
            } catch(Exception e) {
                log.error("Element createNewCalendarEvent not found!");
            }

            String nameEvent = null;
            try {
                WebElement createNameEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[1]"
                        + "/div/input"));
                nameEvent = "autoTestCalendarEvent_" + n + "_(" + cTime + ")";
                if (nameEvent != null && !nameEvent.isEmpty()) {
                    createNameEventButton.sendKeys(nameEvent);
                }
            } catch (Exception e) {
                log.error("Failed to create events name!");
            }

            try {
                WebElement alwaysActiveButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/"
                        + "div/div/div[2]/div[1]/md-checkbox/div[1]"));
                alwaysActiveButton.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element alwaysActiveButton not found!");
            }

            try {
                WebElement startButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]"
                        + "/div[1]/div/md-checkbox/div[1]"));
                startButton.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element startButton not found!");
            }

            try {
                WebElement startField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                        + "div[2]/div[1]/div/input"));
                startField.click();
            } catch (Exception e) {
                log.error("Element startField not found!");
            }

            try {
                WebElement monthButton = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/"
                        + "div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/"
                        + "bs-datepicker-navigation-view/button[2]/span"));
                monthButton.click();
            } catch (Exception e) {
                log.error("Element monthButton not found!");
            }

            try {
                WebElement monthSelectJune = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                        + "div/bs-month-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[3]/span"));
                monthSelectJune.click();
            } catch (Exception e) {
                log.error("Element monthSelectJune not found!");
            }

            try {
                WebElement daySelect10 = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                        + "div/bs-days-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[3]/td[3]/span"));
                daySelect10.click();
            } catch (Exception e) {
                log.error("Element daySelect10  not found!");
            }

            try {
                WebElement hourField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                        + "div[2]/div[1]/div/timepicker/table/tbody/tr[2]/td[1]/input"));
                hourField.sendKeys("06");
            } catch (Exception e) {
                log.error("Element hourField not found!");
            }

            try {
                WebElement minuteField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                        + "div[2]/div[1]/div/timepicker/table/tbody/tr[2]/td[3]/input"));
                minuteField.sendKeys("25");
            } catch (Exception e) {
                log.error("Element minuteField not found!");
            }
            milliSleep(delayMilliSec);

            try {
                WebElement endButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/"
                        + "div/md-checkbox/div[1]"));
                endButton.click();
            } catch (Exception e) {
                log.error("Element endButton not found!");
            }

            try {
                WebElement endField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/"
                        + "div[2]/div[2]/div/input"));
                endField.click();
            } catch (Exception e) {
                log.error("Element endField not found!");
            }

            try {
                WebElement month2Button = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div"
                        + "/div/bs-days-calendar-view/bs-calendar-layout/div[1]/"
                        + "bs-datepicker-navigation-view/button[2]/span"));
                month2Button.click();
            } catch (Exception e) {
                log.error("Element month2Button not found");
            }

            try {
                WebElement month2SelectAugust = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/"
                        + "div/div/bs-month-calendar-view/bs- calendar-layout/div[2]/table/tbody/tr[3]/td[2]/span"));
                month2SelectAugust.click();
            } catch (Exception e) {
                log.error("Element month2SelectAugust not found!");
            }

            try {
                WebElement day2Select20 = driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/"
                        + "div/bs-days-calendar-view/bs-calendar-layout/div[2]/table/tbody/tr[4]/td[5]"));
                day2Select20.click();
            } catch (Exception e) {
                log.error("Element day2Select20");
            }

            try {
                WebElement hour2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/"
                        + "div/timepicker/table/tbody/tr[2]/td[1]/input"));
                hour2Field.sendKeys("11");
            } catch (Exception e) {
                log.error("Element hour2Field not found!");
            }

            try {
                WebElement minute2Field = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[2]/div[2]/"
                        + "div[2]/div/timepicker/table/tbody/tr[2]/td[3]/input"));
                minute2Field.sendKeys("15");
            } catch (Exception e) {
                log.error("Element minute2Field not found!");
            }

            try {
                WebElement everyHour = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[1]/div[2]"));
                everyHour.click();
            } catch (Exception e) {
                log.error("Element everyHour not found!");
            }

            try {
                WebElement triggerMinute = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[1]/input"));
                triggerMinute.sendKeys("50");   //  50 minutes
            } catch (Exception e) {
                log.error("Element triggerMinute not found!");
            }

            try {
                WebElement triggerHour = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/"
                        + "div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/trigger-edit/"
                        + "div/div[3]/div[2]/table/tbody/tr[2]/td[5]"));
                triggerHour.click();    // 10 hours
            }catch (Exception e) {
                log.error("Element triggerHour not found!");
            }

            try {
                WebElement triggerDay10 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[3]/table/tbody/tr[2]/td[2]"));
                WebElement triggerDay15 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[3]/table/tbody/tr[2]/td[7]"));
                WebElement triggerDay20 = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[3]/table/tbody/tr[3]/td[4]"));
                triggerDay10.click();
                triggerDay15.click();
                triggerDay20.click();
            } catch (Exception e) {
                log.error("Failed to set triggers days");
            }

            try {
                WebElement triggerMonthJune = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[2]"));
                WebElement triggerMonthJule = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[3]"));
                WebElement triggerMonthAugust = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[3]/div/"
                        + "trigger-edit/div/div[3]/div[4]/table/tbody/tr[2]/td[4]"));
                triggerMonthJune.click();
                triggerMonthJule.click();
                triggerMonthAugust.click();
            } catch (Exception e) {
                log.error("Failed to set triggers months");
            }

            try {
                WebElement addAction = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[2]/div/div/div[4]/"
                        + "label/button"));
                addAction.click();
            } catch (Exception e) {
                log.error("Element addAction not found!");
            }


            try {
                List<WebElement> actionsList = driver.findElements(By.tagName("event-action"));
                log.debug("____________________ size of actionsList = " + actionsList.size());
            } catch (Exception e) {
                log.error("Element actionsList not found!");
            }

            try {
                WebElement selectAction = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dialog-select-actions/dm-dialog/div/div/div/div[2]"
                        + "/div/div/div/list-view/event-action[3]/atr-suite/div/md-checkbox/div[1]"));
                selectAction.click();
            } catch (Exception e) {
                log.error("Element selectActions not found!");
            }

            try {
                WebElement addActionApply = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dialog-select-actions/dm-dialog/div/div/div/div[3]/"
                        + "div/div[1]/button"));
                addActionApply.click();
            } catch (Exception e) {
                log.error("Element addActionApply not found!");
            }

            try {
                WebElement saveNewCalendarEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-event/dm-dialog/div/div/div/div[3]/div/div[1]/button"));
                saveNewCalendarEventButton.click();
            } catch (Exception e) {
                log.error("Element saveNewCalendarEventButton not found!");
            }

            log.info("___________________ Calendar event creation was successful!");

            eventNameList.add(nameEvent);

            mSleep(2);
        }

        //======================================== ADD EXTERNAL EVENT ======================================

        for (int m = 0; m < numberExternalEvents; ++m) {
            try {
                mainPage = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
                mainPage.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element mainPage not found! (external events)");
            }

            try {
                libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                        + "/div[1]/div[1]/div[5]/span"));
                libraryButton.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element libraryButton not found! (external events)");
            }

            try {
                eventsButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]/div[2]/div[5]"));
                eventsButton.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element eventsButton not found! (external events)");
            }

            try {
                WebElement createExternalEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/ng-component/div/div/list-view-toolbar-complex/list-view-toolbar/div/"
                        + "div/div[4]/button[2]"));
                createExternalEvent.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element createExternalEvent not found! (external events)");
            }

            String nameExternalEvent = new String();
            try {
                WebElement createNameExternalEvent = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]/"
                        + "div/div/div/div/div[1]/div/input"));
                nameExternalEvent = "autoTestExternalEvent_" + m + "_(" + cTime + ")";
                if (nameExternalEvent != null && !nameExternalEvent.isEmpty()) {
                    createNameExternalEvent.sendKeys(nameExternalEvent);
                }
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element createNameExternalEvent not found! (external events)");
            }

            try {
                WebElement templateField = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]"
                        + "/div/div/div/div/div[2]/select"));
                templateField.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element templateField not found! (external events)");
            }

            try {
                WebElement selectTemplate = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                        + "ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[2]/"
                        + "div/div/div/div/div[2]/select/option[3]"));
                selectTemplate.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element selectTemplate not found! (external events)");
            }

            try {
                WebElement saveExternalEventButton = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]"
                        + "/ng-component/div/ng-component/dialog-external-event/dm-dialog/div/div/div/div[3]/"
                        + "div/div[1]/button"));
                saveExternalEventButton.click();
                milliSleep(delayMilliSec);
            } catch (Exception e) {
                log.error("Element saveExternalEventButton not found! (external events)");
            }

            log.info("___________________ External event creation was successful!");

            eventNameList.add(nameExternalEvent);

            log.debug("___________________ eventNameList.size = " + eventNameList.size());
            mSleep(2);
        }
        //===================================================================================================

        log.info("============================ DELETE EVENTS ==========================");
        //================================== DELETE CREATED EVENTS =========================================
        if(eventNameList.size() > 0) {
            TypeEvent typeEvent = TypeEvent.UNDEFINED;
            for(int i = 0; i < eventNameList.size(); i++) {
                try {
                    driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));  //  mainPage
                } catch (Exception e) {
                    log.error("Element mainPage not found! (delete events)");
                }

                try {
                    libraryButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[2]"
                            + "/div[1]/div[1]/div[5]/span"));
                    libraryButton.click();
                } catch (Exception e) {
                    log.error("Element libraryButton not found! (delete events)");
                }

                try {
                    eventsButton.click();
                } catch (Exception e) {
                    log.error("Element eventButton not found! (delete events)");
                }

                //============================== DELETE CALENDAR EVENTS ===================================
                List<WebElement> listEvents = driver.findElements(By.tagName("calendar-event"));
                log.debug("___________________ listCharts for delete - OK, number events: " + listEvents.size());

                WebElement oneCalendarEvent = null;

                for(int k = 0; k < listEvents.size(); ++k) {
                    log.debug("___________________ it is entry ...  iteration = " + k);
                    WebElement temp = listEvents.get(k);

                    try {
                        oneCalendarEvent = temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/"
                                +"ng-component/div/ng-component/div/div/div/div/list-view/"
                                +"calendar-event[" + (k + 1) + "]/div/div[3]/div[1]/div[1]/div"));

                        String atrib = oneCalendarEvent.getText();
                        log.debug("- - - - - - - - " + atrib);
                        if(atrib.equals(eventNameList.get(i))) {
                            typeEvent = TypeEvent.CALENDAR;
                            log.info("___________________ This event is CALENDAR type!!!");
                            try {
                                temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/"
                                        + "ng-component/div/div/div/div/list-view/calendar-event[" + (k + 1) + "]"));
                                milliSleep(delayMilliSec);
                            }catch (Exception e) {
                                log.error("Element temp not found! (delete calendar events)");
                            }

                            try {
                                WebElement eventDeleteButton = driver.findElement(By.xpath("/html/body/app-root/div/"
                                        + "div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                        + "calendar-event[" + (k + 1) + "]/div/div[3]/div[1]/div[4]/button[1]"));
                                eventDeleteButton.click();
                                milliSleep(delayMilliSec);
                            } catch (Exception e) {
                                log.error("Element eventDeleteButton not found! (delete calendar events)");
                            }

                            try {
                                WebElement eventDeleteApplyButton = driver.findElement(By.xpath("/html/body/app-root/"
                                        + "div/div[2]/div[2]/ng-component/div/dialogs-list/div/div/content-dialog/"
                                        + "div/div[1]/div/div[3]/button"));
                                eventDeleteApplyButton.click();
                                milliSleep(delayMilliSec);
                            } catch (Exception e) {
                                log.error("Element eventDeleteApplyButton not found! (delete calendar events)");
                            }

                            log.info("Calendar event " + atrib + " has been deleted successfully!");
                            break;
                        }
                    }
                    catch (Exception e) {
                        log.error("Exception! Element not found in calendar events");
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
                        log.debug("- - - - - - - - " + atrib);
                        if(atrib.equals(eventNameList.get(i))) {
                            typeEvent = TypeEvent.EXTERNAL;
                            log.info("___________________ This event is EXTERNAL type!!!");
                            try {
                                temp.findElement(By.xpath("/html/body/app-root/div/div[2]/div[2]/ng-component/div/"
                                        + "ng-component/div/div/div/div/list-view/external-event[" + (l + 1) + "]"));
                                milliSleep(delayMilliSec);
                            }catch (Exception e) {
                                log.error("Element temp not found! (delete external event)");
                            }

                            try {
                                WebElement externalEventDeleteButton = driver.findElement(By.xpath("/html/body/app-root/"
                                        + "div/div[2]/div[2]/ng-component/div/ng-component/div/div/div/div/list-view/"
                                        + "external-event[" + (l + 1) + "]/div/div[3]/div[1]/div[4]/button[1]"));
                                externalEventDeleteButton.click();
                                milliSleep(delayMilliSec);
                            } catch (Exception e) {
                                log.error("Element externalEventDeleteButton not found! (delete external events)");
                            }

                            try {
                                WebElement externalEventDeleteApplyButton = driver.findElement(By.xpath("/html/body/"
                                        + "app-root/div/div[2]/div[2]/ng-component/div/dialogs-list/div/div/"
                                        + "content-dialog/div/div[1]/div/div[3]/button"));
                                externalEventDeleteApplyButton.click();
                                milliSleep(delayMilliSec);
                            } catch (Exception e) {
                                log.error("Element externalEventDeleteApplyButton not found!");
                            }

                            log.info("External event " + atrib + " has been deleted successfully!");
                            break;
                        }
                    }
                    catch(Exception e) {
                        log.error("Exception! Element not found in external events");
                    }
                }
            }
        }
        //==================================================================================================
        try {
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/div[1]/div"));
        } catch (Exception e) {
            log.error("Element mainPage not found!");
        }

    }

    @AfterClass
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
    }
}

