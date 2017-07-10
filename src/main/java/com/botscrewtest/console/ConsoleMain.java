package com.botscrewtest.console;

public class ConsoleMain {
    private ContentUserMenu contentUserMenu;

    public ConsoleMain(ContentUserMenu contentUserMenu) {
        this.contentUserMenu = contentUserMenu;
    }

    public void consoleMain() {
        ListMenu.makeMenu();
        contentUserMenu.mainMenu();
    }
}
