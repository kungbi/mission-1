package oncall.view;

import oncall.dto.WorkDto;
import oncall.dto.WorkScheduleDto;

public class OutputView {

    public static void printWorkSchedule(WorkScheduleDto workSchedule) {
        for (WorkDto work : workSchedule.workDtos()) {
            String holiday = "";
            if (work.holiday()) {
                holiday = "(휴일)";
            }

            System.out.printf("%d %d %s%s %s", work.month(), work.day(), work.dayOfWeek(), holiday, work.workerName());
        }

    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
        System.out.println();
    }
}
