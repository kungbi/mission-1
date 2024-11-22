package oncall.config;

import oncall.controller.OnCallScheduleController;

public class DependencyInjector {
    public OnCallScheduleController createController() {
        return new OnCallScheduleController();
    }
}
