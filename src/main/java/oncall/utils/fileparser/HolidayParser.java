package oncall.utils.fileparser;

import java.io.IOException;
import java.util.List;
import oncall.controller.InputParser;

public class HolidayParser {
    public static final int MONTH = 0;
    public static final int YEAR = 1;
    public static final int DESCRIPTION = 2;

    private final Reader reader;

    public HolidayParser(Reader reader) {
        this.reader = reader;
    }

    public HolidayField nextLine() throws IOException {
        List<String> fields = reader.readLine();
        if (fields == null || fields.isEmpty()) {
            return null;
        }
        validate(fields);

        return new HolidayField(InputParser.parseInt(fields.get(MONTH)), InputParser.parseInt(fields.get(YEAR)),
                fields.get(DESCRIPTION));
    }

    private static void validate(List<String> fields) {
        if (fields.size() != 3) {
            throw new IllegalArgumentException();
        }
    }
}