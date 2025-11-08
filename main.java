
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        main main = new main();
        main.run_main(); 
    }

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final ui main_ui = new ui();
    private final ui.adminUi adminUi = main_ui.new adminUi();
    private final ui.userUi userUi = main_ui.new userUi();

    ArrayList<books> book = new ArrayList<>();

    void run_main(){
        books.book_list(book, null, null, null, false);

        String name = null;
        boolean running = true;

        while (running){
            try {
                main_ui.access_menu();
                System.out.print(main_ui.center_space()+"Select role: ");
                int role = scanner.nextInt();
                switch (role) {
                    case 1 -> access_admin();
                    case 2 -> access_user(name);
                    default -> main_ui.error_message(1, 2);
                }
            } catch (Exception InputMismatchException) {
                main_ui.inputMismatchError_message(1, 2);
                scanner.nextLine();
            }
        }
    }

    void access_admin(){
        System.out.print(main_ui.center_space() + "Enter admin password: ");
        String password = scanner.next();

        boolean in_admin = (password.equals(books.getLibAuth()));
        if (!in_admin) {
            System.out.println(main_ui.center_space() + "Wrong password!");
        }
        while (in_admin) {
            try {
                adminUi.admin_menu(book);
                int menu_options = scanner.nextInt();

                switch (menu_options) {
                    case 1 -> addBook(); // ADDBOOK FUNCTION
                    case 2 -> removeBook(); // REMOVEBOOK FUNCTION
                    case 3 -> {
                        System.out.println(main_ui.center_space() + "Returning to menu...");
                        in_admin = false;
                    } // RETURN TO MAIN MENU
                    case 4 -> System.exit(0); // EXIT PROGRAM
                    default -> main_ui.error_message(1, 4);
                }
            } catch (Exception InputMismatchException) {
                main_ui.inputMismatchError_message(1, 4);
                scanner.nextLine();
            }

        }
    }

    void addBook (){
        adminUi.add_menu();
        int number_added_books = scanner.nextInt();
        scanner.nextLine();
        books.add_book(book, scanner, false, null, number_added_books, null);
    }

    void removeBook(){
        adminUi.remove_menu(book);
        int remove_selection = scanner.nextInt() - 1;
        scanner.nextLine();
        book.get(remove_selection).remove_book(scanner, book, remove_selection);
    }

    void access_user(String name){
        boolean in_user = (name != null);

        if (!in_user) {
            System.out.print(main_ui.center_space() + "Enter your name (NO SPACES): ");
            name = scanner.next();

            in_user = true;
        }

        while (in_user) { 
            try {
                userUi.user_menu();
                int options = scanner.nextInt();
                switch (options) {
                    case 1 -> main_ui.book_list(book, book.size());
                    case 2 -> borrowBook(book, name);
                    case 3 -> books.return_book(book, name, scanner);
                    case 4 -> {
                        System.out.print(main_ui.center_space()+"Enter your name (NO SPACES): ");
                        name = scanner.next();
                    }
                    case 5 -> {
                        System.out.println(main_ui.center_space()+"Returning to menu...");
                        in_user = false;
                    }
                    case 6 -> {
                        System.exit(0);
                    }
                    default -> main_ui.error_message(1, 2);
                }
            } catch (Exception InputMismatchException) {
                main_ui.inputMismatchError_message(1, 6);
                scanner.nextLine();
            }
        }
                        
    }

    void borrowBook(ArrayList<books> book, String name){
        main_ui.book_list(book, book.size());
        System.out.println();
        System.out.print(main_ui.center_space() + "Which book do you want to borrow (Press '0' to return to menu): ");
        int borrowed_book = scanner.nextInt() - 1;
        if (borrowed_book < 0) {
            return;
        }
        scanner.nextLine();

        book.get(borrowed_book).borrow_book(book, borrowed_book, random, name);
    }
}
