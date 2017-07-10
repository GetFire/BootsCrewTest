package com.botscrewtest.console;

import java.util.ArrayList;
import java.util.List;

public class ListMenu {
    private static List<String> serviceHeader = new ArrayList<>();
    private static List<String> serviceHeaderBook = new ArrayList<>();
    private static List<String> serviceMainMenu = new ArrayList<>();
    private static List<String> serviceBookMenu = new ArrayList<>();

    private ListMenu() {
    }

    public static void makeMenu() {
        serviceHeader.add("**************************************************************");
        serviceHeader.add("\t\t Welcome to BootsCrew book manager");
        serviceHeader.add("**************************************************************");
        serviceHeader.add("\n\t Main menu");
        serviceMainMenu.add("Book store");
        serviceHeaderBook.add("**************************************************************");
        serviceHeaderBook.add("\n\t Book menu");
        serviceHeaderBook.add("**************************************************************");

        serviceBookMenu.add("Get all books");
        serviceBookMenu.add("Add new book");
        serviceBookMenu.add("Find book by name");
        serviceBookMenu.add("Edit book");
        serviceBookMenu.add("Delete book");
        serviceBookMenu.add("Return to main menu");
        serviceMainMenu.add("Exit");
    }


    public static List<String> getServiceHeader() {
        return serviceHeader;
    }

    public static List<String> getServiceHeaderBook() {
        return serviceHeaderBook;
    }

    public static List<String> getServiceMainMenu() {
        return serviceMainMenu;
    }

    public static List<String> getServiceBookMenu() {
        return serviceBookMenu;
    }

}
