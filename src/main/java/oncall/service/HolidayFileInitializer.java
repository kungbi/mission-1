package oncall.service;

import java.io.IOException;
import oncall.domain.Holiday;
import oncall.repository.HolidayRepository;
import oncall.utils.fileparser.HolidayField;
import oncall.utils.fileparser.HolidayParser;

public class HolidayFileInitializer {
    private final HolidayRepository holidayRepository;
    private final HolidayParser holidayParser;

    public HolidayFileInitializer(HolidayRepository holidayRepository, HolidayParser holidayParser) {
        this.holidayRepository = holidayRepository;
        this.holidayParser = holidayParser;
    }

    public void init() {
        try {
            while (true) {
                HolidayField holidayField = holidayParser.nextLine();
                if (holidayField == null) {
                    break;
                }
                holidayRepository.add(new Holiday(holidayField.month(), holidayField.day()));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse holiday file", e);
        }
    }

}
