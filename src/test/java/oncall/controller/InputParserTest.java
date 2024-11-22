package oncall.controller;

import oncall.dto.NamesDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void parseMonthAndDayOfWeek_콤마가_없을_때() {
        String input = "12월";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseMonthAndDayOfWeek(input));
    }

    @Test
    void parseMonthAndDayOfWeek_빈_문자열() {
        String input = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseMonthAndDayOfWeek(input));
    }

    @Test
    void parseMonthAndDayOfWeek_콤마_한개() {
        String input = ",";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseMonthAndDayOfWeek(input));
    }

    @Test
    void parseMonthAndDayOfWeek_콤마_두개() {
        String input = ",,";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseMonthAndDayOfWeek(input));
    }

    @Test
    void parseMonthAndDayOfWeek_존재하지_않는_요일() {
        String input = "12,와";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseMonthAndDayOfWeek(input));
    }

    @Test
    void parseNames_빈_문자열() {
        String input = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseNames(input));
    }

    @Test
    void parseNames() {
        String input = "a,b,c";
        NamesDto namesDto = InputParser.parseNames(input);
        Assertions.assertEquals("a", namesDto.names().get(0));
        Assertions.assertEquals("b", namesDto.names().get(1));
        Assertions.assertEquals("c", namesDto.names().get(2));
    }

}