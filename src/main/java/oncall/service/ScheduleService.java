package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Person;
import oncall.domain.WorkScheduler;
import oncall.dto.MakeScheduleInputDto;
import oncall.dto.WorkDto;
import oncall.dto.WorkScheduleDto;
import oncall.enums.DayOfWeek;
import oncall.repository.HolidayRepository;

public class ScheduleService {
    private final HolidayRepository holidayRepository;
    private final WorkScheduler workScheduler;

    public ScheduleService(HolidayRepository holidayRepository, WorkScheduler workScheduler) {
        this.holidayRepository = holidayRepository;
        this.workScheduler = workScheduler;
    }

    public WorkScheduleDto makeSchedule(MakeScheduleInputDto input) {
        List<WorkDto> works = new ArrayList<>();
        DayOfWeek currentDayOfWeek = input.dayOfWeek();
        for (int day = 1; day <= input.numberOfDaysPerMonth(); day++) {
            boolean isHoliday = holidayRepository.findByDate(input.month(), day).isPresent();
            if (DayOfWeek.isWeekend(currentDayOfWeek) || isHoliday) {
                Person holydaysTurnPerson = this.workScheduler.getHolydaysTurnPerson();
                works.add(new WorkDto(input.month(), day, isHoliday, currentDayOfWeek,
                        holydaysTurnPerson.getName()));
                currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek);
                continue;
            }
            Person weekdaysTurnPerson = this.workScheduler.getWeekdaysTurnPerson();
            works.add(
                    new WorkDto(input.month(), day, false, currentDayOfWeek,
                            weekdaysTurnPerson.getName()));
            currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek);
        }
        return new WorkScheduleDto(works);
    }
}
