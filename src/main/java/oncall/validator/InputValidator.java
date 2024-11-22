package oncall.validator;

import java.util.List;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.exception.GlobalErrorMessage;

public class InputValidator {
    public static void monthAndDayOfWeekValidate(MonthAndDayOfWeekDto monthAndDayOfWeek) {
        if (monthAndDayOfWeek == null) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (!(1 <= monthAndDayOfWeek.month() && monthAndDayOfWeek.month() <= 12)) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public static void namesValidate(NamesDto namesDto) {
        List<String> names = namesDto.names();
        if (names == null) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (names.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
