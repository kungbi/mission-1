package oncall.controller;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.dto.WeekAndHolidaysWorkerNamesDto;
import oncall.validator.InputValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class RetryInputUtil {

    public static MonthAndDayOfWeekDto getMonthAndDayOfWeek() {
        return retryLogics(InputView::getMonthAndDayOfWeek, InputParser::parseMonthAndDayOfWeek,
                InputValidator::monthAndDayOfWeekValidate);
    }

    public static WeekAndHolidaysWorkerNamesDto getWeekAndHolidaysWorkerNames() {
        while (true) {
            try {
                NamesDto weekDaysWorkerNames = InputParser.parseNames(InputView.getWeekDaysWorkerNames());
                NamesDto holidaysWorkerNames = InputParser.parseNames(InputView.getHolidaysWorkerNames());

                InputValidator.namesValidate(weekDaysWorkerNames);
                InputValidator.namesValidate(holidaysWorkerNames);

                return new WeekAndHolidaysWorkerNamesDto(
                        weekDaysWorkerNames,
                        holidaysWorkerNames
                );
            } catch (IllegalArgumentException error) {
                OutputView.printError(error.getMessage());
            }
        }
    }

    private static <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser,
                                     Consumer<T> validator) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                T parsedInput = parser.apply(userInput);
                validator.accept(parsedInput);
                return parsedInput;
            } catch (IllegalArgumentException error) {
                OutputView.printError(error.getMessage());
            }
        }
    }
}
