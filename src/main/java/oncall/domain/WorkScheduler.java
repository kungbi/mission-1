package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;

public class WorkScheduler {
    private final WorkingTurn weekdaysTurn;
    private final WorkingTurn holidaysTurn;
    private Person recentlyPerson = null;

    public WorkScheduler(WorkingTurn weekdaysTurn, WorkingTurn holidaysTurn) {
        this.weekdaysTurn = weekdaysTurn;
        this.holidaysTurn = holidaysTurn;
    }

    public Person getWeekdaysTurnPerson() {
        String recentlyPersonName = null;
        if (recentlyPerson != null) {
            recentlyPersonName = recentlyPerson.getName();
        }

        recentlyPerson = weekdaysTurn.getNextPerson(recentlyPersonName);
        return recentlyPerson;
    }

    public Person getHolydaysTurnPerson() {
        String recentlyPersonName = null;
        if (recentlyPerson != null) {
            recentlyPersonName = recentlyPerson.getName();
        }

        recentlyPerson = holidaysTurn.getNextPerson(recentlyPersonName);
        return recentlyPerson;
    }

    public static WorkScheduler of(WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames) {
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
        return new WorkScheduler(weekdaysWorkingTurn, holidaysWorkingTurn);
    }
}
