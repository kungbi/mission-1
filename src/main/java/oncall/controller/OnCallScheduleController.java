package oncall.controller;

import java.util.List;
import oncall.domain.WorkScheduler;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;
import oncall.dto.WorkDto;
import oncall.dto.WorkScheduleDto;
import oncall.enums.NumberOfDaysPerMonth;
import oncall.repository.HolidayRepository;
import oncall.service.ScheduleService;
import oncall.view.OutputView;

public class OnCallScheduleController {
    private final HolidayRepository holidayRepository;
    private final ScheduleService scheduleService;

    public OnCallScheduleController(HolidayRepository holidayRepository, ScheduleService scheduleService) {
        this.holidayRepository = holidayRepository;
        this.scheduleService = scheduleService;
    }

    public void run() {
        MonthAndDayOfWeekDto monthAndDayOfWeek = RetryInputUtil.getMonthAndDayOfWeek();
        WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames = RetryInputUtil.getWeekAndHolidaysWorkerNames();

        WorkScheduler workScheduler = WorkScheduler.of(weekAndHolidaysWorkerNames);
        List<WorkDto> works = this.scheduleService.makeSchedule(monthAndDayOfWeek,
                NumberOfDaysPerMonth.findNumberOfDaysPerMonth(monthAndDayOfWeek.month()), workScheduler);

        OutputView.printWorkSchedule(new WorkScheduleDto(works));
    }


}
