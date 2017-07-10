package com.botscrewtest.console;

import com.botscrewtest.controller.BookController;
import com.botscrewtest.entity.Book;

import java.util.List;

public class ContentUserMenu {
    public static final String ERROR_INCORRECT_MENU_ITEM_SELECTED = "ERROR: Incorrect menu item selected \n";
    public static final String BOOK_NOT_FOUND = "Sorry! Such book doesn't exist in our DB, yet :)";

    private BookController bookController;
    private VisualUserMenu visualUserMenu;

    public ContentUserMenu(BookController bookController, VisualUserMenu visualUserMenu) {
        this.bookController = bookController;
        this.visualUserMenu = visualUserMenu;
    }

    public void mainMenu() {
        mark:
        while (true) {
            VisualUserMenu.printListInConsole(ListMenu.getServiceHeader(), ListMenu.getServiceMainMenu());
            Integer action;
            action = Integer.parseInt(visualUserMenu.getValidInput("Choose action", InputType.INTEGER));
            switch (action) {
                case (1):
                    VisualUserMenu.outputSplitLine();
                    bookmenu();
                    break;
                case (2):
                    VisualUserMenu.outputSplitLine();
                    System.out.println("\tThank you for using our manager.");
                    break mark;
                default:
                    VisualUserMenu.outputSplitLine();
                    System.out.println(ERROR_INCORRECT_MENU_ITEM_SELECTED);
            }
        }
    }

    public void bookmenu() {
        mark:
        while (true) {
            VisualUserMenu.printListInConsole(ListMenu.getServiceHeaderBook(), ListMenu.getServiceBookMenu());
            Integer action;
            action = Integer.parseInt(visualUserMenu.getValidInput("Choose action: ", InputType.INTEGER));
            switch (action) {
                case (1):
                    VisualUserMenu.printListInConsole(null, bookController.getAllBooks());
                    break;
                case (2):
                    String name = visualUserMenu.getValidInput("Please enter book name", InputType.STRING);
                    String author = visualUserMenu.getValidInput("Please enter author's credentials", InputType.STRING);
                    bookController.addNewBook(new Book(name, author));
                    System.out.println("Book " + author + " \"" + name + "\" was  successfully added");
                    break;
                case (3):
                    name = visualUserMenu.getValidInput("Please enter book name", InputType.STRING);

                    List<Book> result = bookController.getBooksByName(name);
                    System.out.println("Our books:");
                    if (result.size() > 0) {
                        result.forEach(book -> System.out.println("\t" + book));
                    } else {
                        System.out.println(BOOK_NOT_FOUND);
                    }

                    break;
                case (4):
                    name = visualUserMenu.getValidInput("Please enter book name which you want to edit", InputType.STRING);

                    if (bookController.getBooksByName(name).size() == 0) {
                        System.out.println(BOOK_NOT_FOUND);
                    } else if (bookController.getBooksByName(name).size() > 1) {
                        System.out.println("We have few books with such name please choose one by typing a number of book:");
                        for (Book book : bookController.getBooksByName(name)) {
                            System.out.println("#" + book.getId() + " " + book.getAuthor() + " \"" + book.getName() + "\"");
                        }
                        int choice = Integer.valueOf(visualUserMenu.getValidInput("Enter the number: ", InputType.INTEGER));
                        String newName = visualUserMenu.getValidInput("Enter new name: ", InputType.STRING);
                        author = visualUserMenu.getValidInput("Please enter new author's credentials", InputType.STRING);
                        bookController.updateBook(choice, new Book(newName, author));
                        System.out.println("Book " + author + " \"" + name + "\" was successfully updated");
                    } else {
                        String newName = visualUserMenu.getValidInput("Enter new name: ", InputType.STRING);
                        author = visualUserMenu.getValidInput("Please enter new author's credentials", InputType.STRING);
                        bookController.updateBook(bookController.getBooksByName(name).get(0).getId(), new Book(newName, author));
                        System.out.println("Book " + author + " \"" + name + "\" was successfully updated");
                    }

                    break;
                case (5):
                    name = visualUserMenu.getValidInput("Please enter book name which you want to delete", InputType.STRING);
                    if (bookController.getBooksByName(name).size() == 0) {
                        System.out.println(BOOK_NOT_FOUND);
                    } else if (bookController.getBooksByName(name).size() > 1) {
                        System.out.println("We have few books with such name please choose one by typing a number of book:");
                        for (Book book : bookController.getBooksByName(name)) {
                            System.out.println("#" + book.getId() + " " + book.getAuthor() + " " + book.getName());
                        }
                        int choice = Integer.valueOf(visualUserMenu.getValidInput("Enter the number of book which you want to delete: ", InputType.INTEGER));
                        bookController.deleteBook(choice);
                        System.out.println();
                        System.out.println("Book \"" + name + "\" was successfully deleted");
                    } else {
                        bookController.deleteBook(bookController.getBooksByName(name).get(0).getId());
                        System.out.println("Book \"" + name + "\" was successfully deleted");
                    }

                    break;
                case (6):
                    VisualUserMenu.outputSplitLine();
                    break mark;
                default:
                    VisualUserMenu.outputSplitLine();
                    System.out.println(ERROR_INCORRECT_MENU_ITEM_SELECTED);
            }
        }

    }
}
