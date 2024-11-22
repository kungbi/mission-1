package oncall.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import oncall.controller.OnCallScheduleController;
import oncall.repository.HolidayRepository;
import oncall.service.HolidayFileInitializer;
import oncall.utils.fileparser.CsvReader;
import oncall.utils.fileparser.HolidayParser;

public class DependencyInjector {
    public OnCallScheduleController createController() {
        HolidayRepository holidayRepository = new HolidayRepository();
        holidaysInitialize(holidayRepository);

        return new OnCallScheduleController(holidayRepository);
    }

    private void holidaysInitialize(HolidayRepository holidayRepository) {
        HolidayParser holidayParser = new HolidayParser(new CsvReader(this.createBufferedReader("holidays.md"), false));
        HolidayFileInitializer holidayFileInitializer = new HolidayFileInitializer(holidayRepository, holidayParser);
        holidayFileInitializer.init();
    }

    private BufferedReader createBufferedReader(String fileName) {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(DependencyInjector.class.getClassLoader().getResourceAsStream(fileName))));
    }
}
