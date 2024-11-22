package oncall.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void monthAndDayOfWeekValidateTest() {
        InputValidator.monthAndDayOfWeekValidate("12,");
    }

}