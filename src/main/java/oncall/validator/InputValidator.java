package oncall.validator;

import java.util.List;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;

public class InputValidator {
    public static void monthAndDayOfWeekValidate(MonthAndDayOfWeekDto monthAndDayOfWeek) {
        if (monthAndDayOfWeek == null) {
            throw new IllegalArgumentException("monthAndDayOfWeek is null");
        }

        if (!(1 <= monthAndDayOfWeek.month() && monthAndDayOfWeek.month() <= 12)) {
            throw new IllegalArgumentException("monthAndDayOfWeek.month must be between 1 and 12");
        }
    }

    public static void namesValidate(NamesDto namesDto) {
        List<String> names = namesDto.names();
        if (names == null) {
            throw new IllegalArgumentException("names is null");
        }

        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("names must contain duplicate elements");
        }

        if (names.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("names must contain at least one character");
        }
    }
}
