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
            works.add(makeSingleSchedule(input, day, currentDayOfWeek));
            currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek);
        }
        return new WorkScheduleDto(works);
    }

    private WorkDto makeSingleSchedule(MakeScheduleInputDto input, int day, DayOfWeek currentDayOfWeek) {
        boolean isHoliday = holidayRepository.findByDate(input.month(), day).isPresent();
        if (DayOfWeek.isWeekend(currentDayOfWeek) || isHoliday) {
            return makeHolidayWork(input, day, currentDayOfWeek, isHoliday);
        }
        return makeWeekdayWork(input, day, currentDayOfWeek);
    }

    private WorkDto makeWeekdayWork(MakeScheduleInputDto input, int day, DayOfWeek currentDayOfWeek) {
        Person weekdaysTurnPerson = this.workScheduler.getWeekdaysTurnPerson();
        return new WorkDto(input.month(), day, false, currentDayOfWeek, weekdaysTurnPerson.getName());
    }

    private WorkDto makeHolidayWork(MakeScheduleInputDto input, int day, DayOfWeek currentDayOfWeek,
                                    boolean isHoliday) {
        Person holydaysTurnPerson = this.workScheduler.getHolydaysTurnPerson();
        return new WorkDto(input.month(), day, isHoliday, currentDayOfWeek, holydaysTurnPerson.getName());
    }
}
