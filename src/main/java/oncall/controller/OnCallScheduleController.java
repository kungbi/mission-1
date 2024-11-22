package oncall.controller;

import oncall.domain.WorkScheduler;
import oncall.dto.MakeScheduleInputDto;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;
import oncall.dto.WorkScheduleDto;
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

        ScheduleService scheduleService = createSchedulerService(weekAndHolidaysWorkerNames);
        WorkScheduleDto workScheduleDto = scheduleService.makeSchedule(
                new MakeScheduleInputDto.Builder()
                        .month(monthAndDayOfWeek.month())
                        .dayOfWeek(monthAndDayOfWeek.dayOfWeek())
                        .numberOfDaysPerMonth(getNumberOfDaysPerMonth(monthAndDayOfWeek.month()))
                        .build()
        );

        OutputView.printWorkSchedule(workScheduleDto);
    }

    private ScheduleService createSchedulerService(WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames) {
        WorkScheduler workScheduler = WorkScheduler.from(weekAndHolidaysWorkerNames);
        return new ScheduleService(holidayRepository, workScheduler);
    }

    private int getNumberOfDaysPerMonth(int month) {
        NumberOfDaysPerMonth numberOfDaysPerMonth = NumberOfDaysPerMonth.findNumberOfDaysPerMonth(month);
        return numberOfDaysPerMonth.getNumberOfDays();
    }
}
