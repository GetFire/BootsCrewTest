package com.botscrewtest.utils;

import com.botscrewtest.console.ConsoleMain;
import com.botscrewtest.console.ContentUserMenu;
import com.botscrewtest.console.VisualUserMenu;
import com.botscrewtest.controller.BookController;
import com.botscrewtest.dao.BookDao;
import com.botscrewtest.service.BookService;

import java.sql.Connection;

public class Injector {

    private static Connection connection = ConnectionUtil.getConnection();
    private static BookDao bookDao = new BookDao("books", "id", connection);
    private static BookService bookService = new BookService(bookDao);
    private static BookController bookController = new BookController(bookService);
    private static VisualUserMenu visualUserMenu = new VisualUserMenu();
    private static ContentUserMenu contentUserMenu = new ContentUserMenu(bookController, visualUserMenu);
    private static ConsoleMain consoleMain = new ConsoleMain(contentUserMenu);

    public static ConsoleMain getConsoleMain() {
        return consoleMain;
    }
}
