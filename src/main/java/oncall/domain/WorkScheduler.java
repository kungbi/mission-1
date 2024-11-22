package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;

public class WorkScheduler {
    private final WorkingTurn weekdaysTurn;
    private final WorkingTurn holidaysTurn;
    private Person recentlyPerson = null;

    public WorkScheduler(WorkingTurn weekdaysTurn, WorkingTurn holidaysTurn) {
        validate(weekdaysTurn, holidaysTurn);
        this.weekdaysTurn = weekdaysTurn;
        this.holidaysTurn = holidaysTurn;
    }


    // public method

    /**
     * 평일 근무자 순번에서 다음 사람을 뽑습니다.
     * @return Person
     */
    public Person getWeekdaysTurnPerson() {
        String recentlyPersonName = null;
        if (recentlyPerson != null) {
            recentlyPersonName = recentlyPerson.getName();
        }

        recentlyPerson = weekdaysTurn.getNextPerson(recentlyPersonName);
        return recentlyPerson;
    }

    /**
     * 주말 근무자 순번에서 다음 사람을 뽑습니다.
     * @return Person
     */
    public Person getHolydaysTurnPerson() {
        String recentlyPersonName = null;
        if (recentlyPerson != null) {
            recentlyPersonName = recentlyPerson.getName();
        }

        recentlyPerson = holidaysTurn.getNextPerson(recentlyPersonName);
        return recentlyPerson;
    }


    // private method

    private void validate(WorkingTurn weekdaysTurn, WorkingTurn holidaysTurn) {
        if (weekdaysTurn == null) {
            throw new IllegalArgumentException("weekdaysTurn is null");
        }
        if (holidaysTurn == null) {
            throw new IllegalArgumentException("holidaysTurn is null");
        }
    }


    // static method

    /**
     * 정적 팩토리 메서드로, 평일과 휴일 근무자들의 이름을 인자로 받습니다.
     * 이름을 통해 평일과 휴일 순번을 만들고 WorkScheduler 객체를 생성합니다.
     * @param weekAndHolidaysWorkerNames
     * @return WorkScheduler
     */
    public static WorkScheduler from(WeekAndHolidaysWorkerNamesDto weekAndHolidaysWorkerNames) {
        List<String> weekdaysWorkerNames = weekAndHolidaysWorkerNames.weekdaysWorkerNames().names();
        List<String> holidaysWorkerNames = weekAndHolidaysWorkerNames.holidaysWorkerNames().names();

        return new WorkScheduler(createWorkingTurn(weekdaysWorkerNames), createWorkingTurn(holidaysWorkerNames));
    }

    private static WorkingTurn createWorkingTurn(List<String> names) {
        List<Person> people = new ArrayList<>();
        for (String name : names) {
            people.add(new Person(name));
        }
        return new WorkingTurn(people);
    }
}
