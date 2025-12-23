package tests;

import org.testng.annotations.Test;
import java.time.LocalDate;

import static Constants.Data.URL;
import static Constants.Locators.*;


public class TravelInsuranceCompanyTest extends BaseTest {

    @Test
    public void WebPagesCheck() {
        goToUrl(URL);
        clickOnElement(FIRST_TIME_PURCHASE_BUTTON);
        clickOnElement(CANADA_BUTTON);
        clickOnElement(CONTINUE_TO_TRAVEL_DATES_BUTTON);
        String todayPlus7Days = String.valueOf(getFutureDate(LocalDate.now(),7));
        insertTextInField(START_DATE_INPUT, todayPlus7Days);
        pickDateFromCalendar(getFutureDate(LocalDate.now().plusDays(7),30));
        verifyElementText(START_DATE_VALUE, String.valueOf(getFutureDate(LocalDate.now(),7)));
        verifyElementText(END_DATE_VALUE, String.valueOf(String.valueOf(getFutureDate(LocalDate.now(),37))));
        clickOnElement(PASSENGER_DETAILS_BUTTON);
        isElementDisplayed(FIRST_PASSENGER_DETAILS_PAGE_TITLE);
    }
}
