package oncall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.enums.DayOfWeek;

public class InputParser {

    public static final int MONTH_INDEX = 0;
    public static final int DAY_OF_WEEK_INDEX = 1;

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static MonthAndDayOfWeekDto parseMonthAndDayOfWeek(String input) {
        List<String> splitInput = Arrays.stream(input.split(",")).toList();
        if (splitInput.size() != 2) {
            throw new IllegalArgumentException();
        }

        int month = parseInt(splitInput.get(MONTH_INDEX));
        DayOfWeek dayOfWeek = DayOfWeek.findByKorean(splitInput.get(DAY_OF_WEEK_INDEX));
        return new MonthAndDayOfWeekDto(month, dayOfWeek);
    }

    public static NamesDto parseNames(String input) {
        List<String> splitInput = Arrays.stream(input.split(",")).toList();

        List<String> names = new ArrayList<>(splitInput);
        return new NamesDto(names);
    }
}
