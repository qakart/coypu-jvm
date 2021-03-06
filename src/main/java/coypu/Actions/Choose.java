package coypu.Actions;

import coypu.*;

public class Choose extends DriverAction {
    private DriverScope scope;
    private String locator;

    public Choose(Driver driver, DriverScope scope, String locator, Options options) {
        super(driver, options);
        this.scope = scope;
        this.locator = locator;
    }

    public void act() {
        Driver.choose(Driver.findField(locator, scope));
    }
}
