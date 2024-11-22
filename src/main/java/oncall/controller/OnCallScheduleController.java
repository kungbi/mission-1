package oncall.controller;

import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;

public class OnCallScheduleController {

    public void run() {
        MonthAndDayOfWeekDto monthAndDayOfWeek = RetryInputUtil.getMonthAndDayOfWeek();
        WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames = RetryInputUtil.getWeekAndHolidaysWorkerNames();


    }

}
