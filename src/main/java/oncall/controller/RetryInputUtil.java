package oncall.controller;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import oncall.dto.MonthAndDayOfWeekDto;
import oncall.dto.NamesDto;
import oncall.validator.InputValidator;
import oncall.view.InputView;

public class RetryInputUtil {

    public static MonthAndDayOfWeekDto getMonthAndDayOfWeek() {
        return retryLogics(InputView::getMonthAndDayOfWeek, InputParser::parseMonthAndDayOfWeek,
                InputValidator::monthAndDayOfWeekValidate);
    }

    public static NamesDto getWeekDaysWorkerNames() {
        return retryLogics(InputView::getWeekDaysWorkerNames, InputParser::parseNames, InputValidator::namesValidate);
    }

    public static NamesDto getHolidaysWorkerNames() {
        return retryLogics(InputView::getHolidaysWorkerNames, InputParser::parseNames, InputValidator::namesValidate);
    }

    public static <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser,
                                    Consumer<T> validator) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                T parsedInput = parser.apply(userInput);
                validator.accept(parsedInput);
                return parsedInput;
            } catch (IllegalArgumentException error) {
//                OutputView.printError(error.getMessage());
            }
        }
    }

    public static String retryLogics(Supplier<String> userInputReader, Consumer<String> validator) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                validator.accept(userInput);
                return userInput;
            } catch (IllegalArgumentException error) {
//                OutputView.printError(error.getMessage());
            }

        }
    }
}
