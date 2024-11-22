package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 근무자 이름과 현재까지의 순번에 대한 정보를 관리하고 있습니다.
 * 메서드를 통해 다음 근무자를 받을 수 있습니다.
 * 연속 2일근무로 인해 순번이 밀린 인원은 priorityPeople 에 추가되고 이후에 priorityPeople이 먼저 반환됩니다.
 */
public class WorkingTurn {
    private final List<Person> people;
    private final Queue<Person> priorityPeople;
    private int turn = 0;

    public WorkingTurn(List<Person> persons) {
        validate(persons);
        this.people = persons;
        this.priorityPeople = new ArrayDeque<>();
    }

    // public method

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

    // private method

    private boolean isAvailableWork(String personName) {
        return !people.get(turn).getName().equals(personName);
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
