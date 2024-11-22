package oncall.domain;

public class WorkingPlanner {
    private final WorkingTurn weekdaysTurn;
    private final WorkingTurn holidaysTurn;
    private Person recentlyPerson = null;

    public WorkingPlanner(WorkingTurn weekdaysTurn, WorkingTurn weekendTurn) {
        this.weekdaysTurn = weekdaysTurn;
        this.holidaysTurn = weekendTurn;
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
