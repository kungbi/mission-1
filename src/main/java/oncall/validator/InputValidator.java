package oncall.validator;

import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.exception.GlobalErrorMessage;

public class InputValidator {
    public static void monthAndDayOfWeekValidate(MonthAndDayOfWeekDto monthAndDayOfWeek) {
        if (monthAndDayOfWeek == null) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
        if (monthAndDayOfWeek.dayOfWeek() == null) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (!(1 <= monthAndDayOfWeek.month() && monthAndDayOfWeek.month() <= 12)) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public static void namesValidate(NamesDto namesDto) {
        if (namesDto == null) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
        if (namesDto.names() == null || namesDto.names().isEmpty()) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (namesDto.names().stream().distinct().count() != namesDto.names().size()) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        if (namesDto.names().stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
