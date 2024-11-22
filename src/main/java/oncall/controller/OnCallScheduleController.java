package oncall.controller;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Person;
import oncall.domain.WorkScheduler;
import oncall.domain.WorkingTurn;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;
import oncall.dto.WorkDto;
import oncall.dto.WorkScheduleDto;
import oncall.enums.DayOfWeek;
import oncall.enums.NumberOfDaysPerMonth;
import oncall.repository.HolidayRepository;
import oncall.view.OutputView;

public class OnCallScheduleController {
    private final HolidayRepository holidayRepository;

    public OnCallScheduleController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public void run() {
        MonthAndDayOfWeekDto monthAndDayOfWeek = RetryInputUtil.getMonthAndDayOfWeek();
        WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames = RetryInputUtil.getWeekAndHolidaysWorkerNames();

        List<Person> weekdaysPeople = new ArrayList<>();
        for (String name : weekAndHolidaysWorkerNames.weekdaysWorkerNames().names()) {
            weekdaysPeople.add(new Person(name));
        }

        List<Person> holidaysPeople = new ArrayList<>();
        for (String name : weekAndHolidaysWorkerNames.holidaysWorkerNames().names()) {
            holidaysPeople.add(new Person(name));
        }

        WorkingTurn weekdaysWorkingTurn = new WorkingTurn(weekdaysPeople);
        WorkingTurn holidaysWorkingTurn = new WorkingTurn(holidaysPeople);
        WorkScheduler workScheduler = new WorkScheduler(weekdaysWorkingTurn, holidaysWorkingTurn);

        NumberOfDaysPerMonth numberOfDaysPerMonth = NumberOfDaysPerMonth.findNumberOfDaysPerMonth(
                monthAndDayOfWeek.month());

        List<WorkDto> works = new ArrayList<>();
        DayOfWeek currentDayOfWeek = monthAndDayOfWeek.dayOfWeek();
        for (int day = 1; day <= numberOfDaysPerMonth.getNumberOfDays(); day++) {
            if (DayOfWeek.isWeekend(currentDayOfWeek) || holidayRepository.findByDate(monthAndDayOfWeek.month(), day)
                    .isPresent()) {
                Person holydaysTurnPerson = workScheduler.getHolydaysTurnPerson();
                works.add(new WorkDto(monthAndDayOfWeek.month(), day, true, currentDayOfWeek,
                        holydaysTurnPerson.getName()));
                currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek);
                continue;
            }
            Person weekdaysTurnPerson = workScheduler.getWeekdaysTurnPerson();
            works.add(
                    new WorkDto(monthAndDayOfWeek.month(), day, false, currentDayOfWeek, weekdaysTurnPerson.getName()));
            currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek);
        }

        OutputView.printWorkSchedule(new WorkScheduleDto(works));
    }

}
