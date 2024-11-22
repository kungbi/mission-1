package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Person;
import oncall.domain.WorkScheduler;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.WorkDto;
import oncall.enums.DayOfWeek;
import oncall.enums.NumberOfDaysPerMonth;
import oncall.repository.HolidayRepository;

public class ScheduleService {
    private final HolidayRepository holidayRepository;

    public ScheduleService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<WorkDto> makeSchedule(MonthAndDayOfWeekDto monthAndDayOfWeek,
                                      NumberOfDaysPerMonth numberOfDaysPerMonth, WorkScheduler workScheduler) {
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
        return works;
    }
}
