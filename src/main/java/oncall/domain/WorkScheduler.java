package oncall.domain;

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
}
