package oncall.validator;

import java.util.List;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.enums.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void monthAndDayOfWeekValidate_0월() {
        MonthAndDayOfWeekDto monthAndDayOfWeekDto = new MonthAndDayOfWeekDto(0, DayOfWeek.FRIDAY);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> InputValidator.monthAndDayOfWeekValidate(monthAndDayOfWeekDto));
    }

    @Test
    void monthAndDayOfWeekValidate_13월() {
        MonthAndDayOfWeekDto monthAndDayOfWeekDto = new MonthAndDayOfWeekDto(13, DayOfWeek.FRIDAY);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> InputValidator.monthAndDayOfWeekValidate(monthAndDayOfWeekDto));
    }

    @Test
    void namesValidate_빈_리스트() {
        NamesDto namesDto = new NamesDto(List.of());
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.namesValidate(namesDto));
    }

    @Test
    void namesValidate_중복_이름() {
        NamesDto namesDto = new NamesDto(List.of("a", "b", "a"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.namesValidate(namesDto));
    }

}