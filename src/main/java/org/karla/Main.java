package org.karla;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final List<Book> BOOK_STOCK = List.of(
            new Book(212, "wqa", "Cat in Hat"),
            new Book(153, "gde", "Cat in Bat"),
            new Book(545, "gdf", "Cat in Fat"),
            new Book(645, "rte", "Cat in Iat"),
            new Book(755, "rtt", "Cat in Lat"),
            new Book(435, "fgd", "Cat in Kat"),
            new Book(755, "dfg", "Cat in Jat"),
            new Book(342, "gdg", "Cat in Gat"),
            new Book(545, "fgf", "Cat in Dat"),
            new Book(245, "dgj", "Cat in Sat"),
            new Book(667, "bnv", "Cat in Zat"),
            new Book(346, "ghf", "Cat in Cat"),
            new Book(234, "ncb", "Cat in Vat"),
            new Book(122, "jgh", "Cat in Nat"),
            new Book(333, "bvn", "Cat in Mat"),
            new Book(987, "sdf", "Cat in Qat"),
            new Book(976, "cvc", "Cat in Rat"),
            new Book(789, "cvx", "Cat in Eat"),
            new Book(867, "xvc", "Cat in Pat"),
            new Book(564, "dfs", "Cat in Wat")
    );
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainLoop: while (true) {
            homeScreen();
            String menuSelection = scanner.next();
            switch (menuSelection) {
                case "1":
                    showAvailableBooks();
                    break;
                case "2":
                    showCheckedOutBooks();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    break mainLoop;
                default:
                    System.out.println(menuSelection + " is not a valid option, please try again.");
                    break;
            }
        }
    }

    private static void showAvailableBooks() {
        while (true) {
            List<Book> notCheckedOutBooks = BOOK_STOCK
                    .stream()
                    .filter(book -> !book.isCheckedOut())
                    .toList();
            notCheckedOutBooks.forEach(book -> System.out.println(book));
            System.out.print("Enter a book ID to check out or enter \"exit\" to return to home screen: ");
            String userInput = scanner.next();
            if (userInput.equals("exit")) {
                break;
            }
            Optional<Book> bookToCheckOut =  notCheckedOutBooks
                    .stream()
                    .filter(book -> book.getId() == Integer.parseInt(userInput))
                    .findFirst();
            if (bookToCheckOut.isPresent()) {
                System.out.print("Please enter your name: ");
                String name = scanner.next();
                bookToCheckOut.get().setCheckedOutTo(name);
                bookToCheckOut.get().setCheckedOut(true);
                System.out.println("\"" + bookToCheckOut.get().getTitle() + "\"" + " is checked out");
                break;
            }
            System.out.println("Invalid book ID, please try again");
        }
    }

    private static void showCheckedOutBooks() {
        System.out.println("Showing checked out books");
        /// TODO: implement "show checked out"
    }

    private static void homeScreen() {
        System.out.println("1 - Show Available Books");
        System.out.println("2 - Show Checked Out Books");
        System.out.println("3 - Exit");
        System.out.print("Enter: ");
    }
}