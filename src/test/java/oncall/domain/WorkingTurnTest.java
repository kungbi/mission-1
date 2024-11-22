package oncall.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkingTurnTest {

    @Test
    void getNextTurn() {
        List<Person> people = List.of(new Person("A"), new Person("B"), new Person("C"), new Person("D"),
                new Person("E"));
        WorkingTurn workingTurn = new WorkingTurn(people);

        Person recent = null;
        for (int i = 0; i < 10; i++) {
            if (recent == null) {
                recent = workingTurn.getNextPerson(null);
                continue;
            }
            recent = workingTurn.getNextPerson(recent.getName());
        }
        Assertions.assertEquals("E", recent.getName());
    }


    @Test
    void getNextTurn_근무_불가() {
        // given
        List<Person> people = List.of(new Person("A"), new Person("B"), new Person("C"));
        WorkingTurn workingTurn = new WorkingTurn(people);

        Assertions.assertEquals("B", workingTurn.getNextPerson("A").getName());
        Assertions.assertEquals("A", workingTurn.getNextPerson("B").getName());
        Assertions.assertEquals("A", workingTurn.getNextPerson("C").getName());
    }

}