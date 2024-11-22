package oncall.controller;

import oncall.domain.WorkScheduler;
import oncall.dto.MakeScheduleInputDto;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;
import oncall.dto.WorkScheduleDto;
import oncall.enums.DayOfWeek;
import oncall.enums.NumberOfDaysPerMonth;
import oncall.repository.HolidayRepository;
import oncall.service.ScheduleService;
import oncall.view.OutputView;

public class OnCallScheduleController {
    private final HolidayRepository holidayRepository;

    public OnCallScheduleController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public void run() {
        MonthAndDayOfWeekDto monthAndDayOfWeek = RetryInputUtil.getMonthAndDayOfWeek();
        WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames = RetryInputUtil.getWeekAndHolidaysWorkerNames();

        int month = monthAndDayOfWeek.month();
        DayOfWeek dayOfWeek = monthAndDayOfWeek.dayOfWeek();
        int numberOfDaysPerMonth = getNumberOfDaysPerMonth(month);

        ScheduleService scheduleService = createSchedulerService(weekAndHolidaysWorkerNames);
        WorkScheduleDto workScheduleDto = scheduleService.makeSchedule(
                new MakeScheduleInputDto(month, dayOfWeek, numberOfDaysPerMonth));

        OutputView.printWorkSchedule(workScheduleDto);
    }

    private ScheduleService createSchedulerService(WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames) {
        WorkScheduler workScheduler = WorkScheduler.of(weekAndHolidaysWorkerNames);
        return new ScheduleService(holidayRepository, workScheduler);
    }

    private int getNumberOfDaysPerMonth(int month) {
        NumberOfDaysPerMonth numberOfDaysPerMonth = NumberOfDaysPerMonth.findNumberOfDaysPerMonth(month);
        return numberOfDaysPerMonth.getNumberOfDays();
    }
}
