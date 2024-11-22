package oncall.domain;

import java.time.LocalDate;

public class Working {
    private final LocalDate date;
    private final Person person;

    public Working(LocalDate date, Person person) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }
        this.date = date;
        this.person = person;
    }
}
