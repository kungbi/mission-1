package oncall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.enums.DayOfWeek;
import oncall.exception.GlobalErrorMessage;

public class InputParser {

    public static final int MONTH_INDEX = 0;
    public static final int DAY_OF_WEEK_INDEX = 1;
    public static final String SEPARATOR = ",";
    public static final int MAX_NUMBER_OF_ARGUMENTS = 2;

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage(), error);
        }
    }

    public static MonthAndDayOfWeekDto parseMonthAndDayOfWeek(String input) {
        validateNullAndBlank(input);
        List<String> splitInput = Arrays.stream(input.split(SEPARATOR)).toList();
        if (splitInput.size() != MAX_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }

        int month = parseInt(splitInput.get(MONTH_INDEX));
        DayOfWeek dayOfWeek = DayOfWeek.findByKorean(splitInput.get(DAY_OF_WEEK_INDEX));
        return new MonthAndDayOfWeekDto(month, dayOfWeek);
    }

    public static NamesDto parseNames(String input) {
        validateNullAndBlank(input);
        List<String> splitInput = Arrays.stream(input.split(SEPARATOR)).toList();

        List<String> names = new ArrayList<>(splitInput);
        return new NamesDto(names);
    }

    private static void validateNullAndBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
