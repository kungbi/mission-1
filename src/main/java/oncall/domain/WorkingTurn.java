package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class WorkingTurn {
    private final List<Person> people;
    private final Queue<Person> priorityPeople;
    private int turn = 0;

    public WorkingTurn(List<Person> persons) {
        validate(persons);
        this.people = persons;
        this.priorityPeople = new ArrayDeque<>();
    }

    public Person getNextPerson(String recentPersonName) {
        if (!priorityPeople.isEmpty()) {
            return priorityPeople.poll();
        }

        if (!isAvailableWork(recentPersonName)) {
            priorityPeople.add(people.get(turn));
            increaseTurn();
        }
        Person person = people.get(turn);
        increaseTurn();
        return person;
    }

    public boolean isAvailableWork(String personName) {
        if (people.get(turn).getName().equals(personName)) {

            return false;
        }
        return true;
    }

    private void increaseTurn() {
        turn = (turn + 1) % people.size();
    }

    private void validate(List<Person> persons) {
        if (persons.stream().distinct().count() != persons.size()) {
            throw new IllegalArgumentException("persons must have the same number of people");
        }
        if (persons.size() < 2) {
            throw new IllegalArgumentException("persons must have at least 2 persons");
        }
    }
}
