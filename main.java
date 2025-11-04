
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        ui main_ui = new ui();
        ui.adminUi adminUi = main_ui.new adminUi();
        ui.userUi userUi = main_ui.new userUi();

        String username = null;
        String redeem_code = null;
        boolean availability = false;
        String title = null;

        ArrayList<books> book = new ArrayList<>();
        books.book_list(book, username, redeem_code, title, availability);
        
        main_ui.banner();
        //String name = null;
        //int role;
        boolean running = true;

        OUTER:
        while (running) {
            try {
                main_ui.access_menu();
                System.out.print("Select role: ");
                int role = scanner.nextInt();

                switch (role) {
                    case 1 -> { //Admin side
                        System.out.println("Enter admin password: ");
                        String password = scanner.next();

                        boolean in_admin = (password.equals(books.lib_Auth())); if (!in_admin){System.out.println("Wrong password!");}
                        while (in_admin) {
                            try {
                                adminUi.admin_menu(book);
                                int menu_options = scanner.nextInt();

                                switch (menu_options) {
                                    case 1 -> {
                                        adminUi.add_menu();

                                        int number_added_books = scanner.nextInt();
                                        scanner.nextLine();

                                        books.add_book(book, scanner, availability, title, number_added_books, username);
                                    }
                                    case 2 -> {
                                        adminUi.remove_menu(book);

                                        int remove_selection = scanner.nextInt() - 1;
                                        scanner.nextLine();

                                        book.get(remove_selection).remove_book(scanner, book, remove_selection);
                                    }
                                    case 3 -> {
                                        System.out.println("Returning to menu...");
                                        in_admin = false;
                                    }
                                    case 4 -> {
                                        break OUTER;
                                    }
                                    default -> main_ui.error_message(1, 4);
                                }
                            } catch (Exception InputMismatchException) {
                                main_ui.inputMismatchError_message(1, 4);
                                scanner.nextLine();
                            }
                        }
                    }
                    case 2 -> {//User side
                        String name = null;
                        boolean in_user = (name != null);
                        
                        if (!in_user) {
                            System.out.println("Enter your name (NO SPACES): ");
                            name = scanner.next();
                            System.out.println(name);
                            in_user = true;
                        }
                        
                        while (in_user) {
                            try {
                                userUi.user_menu();
                                int options = scanner.nextInt();

                                switch (options) {
                                    case 1 ->
                                        main_ui.book_list(book, book.size());
                                    case 2 -> {
                                        main_ui.book_list(book, book.size());
                                        System.out.println();
                                        System.out.println("Which book do you want to borrow?");
                                        int borrowed_book = scanner.nextInt() - 1;
                                        scanner.nextLine();

                                        book.get(borrowed_book).borrow_book(book, borrowed_book, random, name);
                                    }
                                    case 3 -> books.return_book(book, name, scanner);
                                    case 4 -> { 
                                        System.out.println("Enter your name (NO SPACES): ");
                                        name = scanner.next();
                                    }
                                    case 5 -> {
                                        System.out.println("Returning to menu...");
                                        in_user = false;
                                    }
                                    case 6 -> {
                                        break OUTER;
                                    }
                                    default -> main_ui.error_message(1, 6);
                                }
                            } catch (Exception InputMismatchException) {
                                main_ui.inputMismatchError_message(1, 6);
                                scanner.nextLine();
                            }
                        }
                    }
                    default ->
                        main_ui.error_message(1, 2);
                }
            } catch (Exception InputMismatchException) {
                main_ui.inputMismatchError_message(1, 2);
                scanner.nextLine();
            }
        }
    }
}
