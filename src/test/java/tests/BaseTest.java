package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static Constants.Locators.*;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void initDriver() {
        customReporter("---------- Start of test ----------");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeDriver() {
        customReporter("---------- End of test ----------");
        if (driver != null) {
            driver.quit();
        }
    }

    public void customReporter(String msg){
        Reporter.log(msg);
        System.out.println(msg);
    }

    private static final Map<Integer, String[]> hebrewMonthsMap = new HashMap<>() {{
        put(1, new String[]{"ינואר"});
        put(2, new String[]{"פברואר"});
        put(3, new String[]{"מרץ"});
        put(4, new String[]{"אפריל"});
        put(5, new String[]{"מאי"});
        put(6, new String[]{"יוני"});
        put(7, new String[]{"יולי"});
        put(8, new String[]{"אוגוסט"});
        put(9, new String[]{"ספטמבר"});
        put(10, new String[]{"אוקטובר"});
        put(11, new String[]{"נובמבר"});
        put(12, new String[]{"דצמבר"});
    }};

    public void goToUrl(String url){
        customReporter("Opening URL " + url);
        driver.get(url);
    }

    public void clickOnElement(String elementLocator){
        customReporter("Click on element " + elementLocator);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementLocator))).click();
    }

    public String getFutureDate(LocalDate date, int days){
        customReporter("Get future date to be + " + days + " from " + date);
        LocalDate futureDate = date.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return futureDate.format(formatter);
    }

    public void insertTextInField(String fieldLocator, String text){
        customReporter("Insert " + text + " in field " + fieldLocator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fieldLocator))).sendKeys(text);
    }

    public void verifyElementText(String locator, String expectedText){
        customReporter("Verify element " + locator + " text is " + expectedText);
        Assert.assertEquals(expectedText, wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getAttribute("value"), "Verify element " + locator + " text is " + expectedText);
    }

    public void isElementDisplayed(String locator){
        customReporter("Is element " + locator + " displayed");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).isDisplayed(), "Is element displayed");
    }

    public void pickDateFromCalendar(String dateValue) {
        customReporter("Pick date in calendar to be " + dateValue);
        String[] dateArr =  dateValue.split("/");
        int targetYear = Integer.parseInt(dateArr[2]);
        int targetMonth = Integer.parseInt(dateArr[1]);
        int targetDay = Integer.parseInt(dateArr[0]);

        boolean isDtePicked = false;
        while (!isDtePicked){
            String currentMonthDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CURRENT_MONTH_HEADER))).getText();

            String[] currentMonthSplit = currentMonthDisplayed.split(" ");
            String displayedMonth = currentMonthSplit[0];
            String displayedYear = currentMonthSplit[1];


            if(targetMonth == parseHebrewMonth(displayedMonth) && targetYear == Integer.parseInt(displayedYear)){
                clickOnElement(getDayInCalendarElement(targetDay));
                isDtePicked = true;
            } else {
                clickOnElement(CALENDAR_NEXT_MONTH_BUTTON);
            }
        }
    }

    public int parseHebrewMonth(String headerText) {
        for (Map.Entry<Integer, String[]> entry : hebrewMonthsMap.entrySet()) {
            for (String variant : entry.getValue()) {
                if (headerText.contains(variant)) {
                    customReporter("Parse Hebrew month from " + headerText + " to " + entry.getKey());
                    return entry.getKey();
                }
            }
        }
        throw new RuntimeException("Could not match any Hebrew month in header: " + headerText);
    }

}
