package oncall;

import oncall.config.DependencyInjector;
import oncall.controller.OnCallScheduleController;

public class Application {
    public static void main(String[] args) {
        DependencyInjector injector = new DependencyInjector();
        OnCallScheduleController controller = injector.createController();

        controller.run();
    }
}
