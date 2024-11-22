package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.config.DependencyInjector;
import oncall.controller.OnCallScheduleController;

public class Application {
    public static void main(String[] args) {
        DependencyInjector injector = new DependencyInjector();
        OnCallScheduleController controller = injector.createController();

        try {
            controller.run();
        } finally {
            Console.close();
        }
    }
}
