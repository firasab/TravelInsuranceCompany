package Constants;

public class Locators {
    public static final String FIRST_TIME_PURCHASE_BUTTON = "//*[@data-hrl-bo=\"purchase-for-new-customer\"]";
    public static final String CANADA_BUTTON = "//*[@data-hrl-bo=\"canada\"]";
    public static final String CONTINUE_TO_TRAVEL_DATES_BUTTON = "//*[@data-hrl-bo=\"wizard-next-button\"]";
    public static final String START_DATE_INPUT = "//*[@data-hrl-bo=\"startDateInput_input\"]";
    public static final String START_DATE_VALUE = "//*[@id=\"travel_start_date\"]";
    public static final String END_DATE_VALUE = "//*[@id=\"travel_end_date\"]";
    public static final String PASSENGER_DETAILS_BUTTON = "//*[@id=\"nextButton\"]";
    public static final String FIRST_PASSENGER_DETAILS_PAGE_TITLE = "//*[@data-hrl-bo=\"traveler_info_0\"]";
    public static final String CALENDAR_NEXT_MONTH_BUTTON = "(//*[@data-hrl-bo=\"arrow-forward\"])[last()]";
    public static final String CURRENT_MONTH_HEADER = "(//*[@class=\"MuiTypography-root MuiTypography-body1 MuiTypography-alignCenter\"])[1]";

    public static String getDayInCalendarElement(int val){
        return "(//*[@class=\"MuiPickersBasePicker-pickerView\"])[1]//button[not(@disabled) and normalize-space(.//span)='" + val + "']";
    }
}


