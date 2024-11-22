package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String getMonthAndDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요");
        return getUserInput();
    }

    public static String getWeekDaysWorkerNames() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요");
        return getUserInput();
    }

    public static String getHolidaysWorkerNames() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요");
        return getUserInput();
    }

    private static String getUserInput() {
        System.out.print("> ");
        return Console.readLine();
    }
}
