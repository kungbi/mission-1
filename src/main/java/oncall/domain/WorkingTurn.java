package oncall.domain;

import java.util.List;

public class WorkingTurn {
    private final List<Person> persons;

    public WorkingTurn(List<Person> persons) {
        this.persons = persons;
    }
}
