package oncall.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlannerTest {

    @Test
    void plannerTest() {
        // given
        List<Person> people = List.of(new Person("A"), new Person("B"), new Person("C"));
        WorkingTurn weekdaysTurn = new WorkingTurn(people);
        WorkingTurn holidaysTurn = new WorkingTurn(people);
        WorkScheduler planner = new WorkScheduler(weekdaysTurn, holidaysTurn);

        // 평일에 A가 근무하고 다음이 주말일 경우 A가 아닌 B, 그리고 또 다음날이 주말일 경우 A
        Assertions.assertEquals("A", planner.getWeekdaysTurnPerson().getName());
        Assertions.assertEquals("B", planner.getHolydaysTurnPerson().getName());
        Assertions.assertEquals("A", planner.getHolydaysTurnPerson().getName());
    }

    @Test
    void plannerTest2() {
        // given
        List<Person> people = List.of(new Person("A"), new Person("B"), new Person("C"));
        WorkingTurn weekdaysTurn = new WorkingTurn(people);
        WorkingTurn holidaysTurn = new WorkingTurn(people);
        WorkScheduler planner = new WorkScheduler(weekdaysTurn, holidaysTurn);

        // 평일 - 휴일 - 평일 - 휴일
        System.out.println(planner.getWeekdaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());
        System.out.println(planner.getHolydaysTurnPerson().getName());

        System.out.println(planner.getWeekdaysTurnPerson().getName());

    }


}