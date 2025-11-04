
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ui {

    public void banner() {
        System.out.println("""
                               __    _ __             ______                __  \r
                              / /   (_) /_  _________/_  __/________ ______/ /__\r
                             / /   / / __ \\/ ___/ __ \\/ / / ___/ __ `/ ___/ //_/\r
                            / /___/ / /_/ / /  / /_/ / / / /  / /_/ / /__/ ,<   \r
                           /_____/_/_.___/_/   \\____/_/ /_/   \\__,_/\\___/_/|_|  \r
                                                                                """ //
        //
        //
        //
        //
        );
    }

    public void book_list(ArrayList<books> book, int array_size) {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                   LIST OF BOOKS                  ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        for (int i = 0; i < array_size; i++) {
            String book_status = book.get(i).getAvailability() ? "Available" : "Borrowed";
            System.out.printf("║  %-3d %-25s | %-10s      ║%n", (i + 1), book.get(i).getTitle(), book_status);
        }

        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    public void complete_status(ArrayList<books> book, int array_size) {
        for (int i = 0; i < array_size; i++) {
            String book_status = book.get(i).getAvailability() ? "Available" : "Borrowed";
            System.out.println("["+ (i + 1) +"] " + book.get(i).getTitle() + " - " + book_status + " - " + book.get(i).getUsername() + " - " + book.get(i).getRedeem_code());
        }
    }

    public void access_menu() {
        System.out.println(" ");
        System.out.println("+----------------------------------------+");
        System.out.println("|        WELCOME TO OUR LIBRARY!         |");
        System.out.println("+----------------------------------------+");
        System.out.println("| Select Role:                           |");
        System.out.println("|   [1] Admin                            |");
        System.out.println("|   [2] User                             |");
        System.out.println("+----------------------------------------+");

    }

    public void error_message(int start, int end) {
        System.out.println("+--------------------------------------+");
        System.out.println("   ERROR: Invalid input                  ");
        System.out.println("  -> Expected integer between " + start + " and " + end);
        System.out.println("+--------------------------------------+");
    }

    public void inputMismatchError_message(int start, int end){
        System.out.println("+--------------------------------------+");
        System.out.println("   ERROR: Input mismatch error                  ");
        System.out.println("  -> Expected integer between " + start + " and " + end);
        System.out.println("+--------------------------------------+");
    }

    public void goodbye_ui() {

    }

    public class adminUi {

        public void admin_menu(ArrayList<books> book) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                 ADMIN DASHBOARD                  ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║               --- BOOK STATUS ---                ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.println();
            complete_status(book, book.size());
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║  [1] Add books   ||  [2] Remove books            ║");
            System.out.println("║  [3] Switch user ||  [4] End program             ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("Select: ");

        }

        public void remove_menu(ArrayList<books> book) {
            System.out.println(" ");
            System.out.println("Select which book to remove ");
            System.out.println(" ");
            complete_status(book, book.size());
            System.out.println("Remove: ");
        }

        public void add_menu() {
            System.out.println("How many books do you wanna add? ");
        }

    }

    public class userUi {

        public void user_menu() {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                       WHAT CAN WE DO FOR YOU TODAY?                          ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [1] Show all books                                                          ║");
            System.out.println("║  [2] Borrow books                                                            ║");
            System.out.println("║  [3] Return books                                                            ║");
            System.out.println("║  [4] Change name                                                             ║");
            System.out.println("║  [5] Switch access                                                           ║");
            System.out.println("║  [6] End program                                                             ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Choose an option: ");
        }

        public void return_menu(ArrayList<books> book, String name, int list_count, Scanner scanner) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                  BORROWED BOOKS                  ║");
            System.out.println("╠══════════════════════════════════════════════════╣");

            for (int i = 0; i < book.size(); i++) {
                if (book.get(i).getAvailability() == false && book.get(i).getUsername().equals(name)) {
                    list_count++;
                    System.out.printf("║  %-3d %-25s | %-10s      ║%n", list_count, book.get(i).getTitle(), book.get(i).getRedeem_code());
                }
            }

            if (list_count == 0) {
                System.out.println("║ You have not borrowed any book                   ║");
                System.out.println("╚══════════════════════════════════════════════════╝");
            } else {
                System.out.println("╚══════════════════════════════════════════════════╝");
                System.out.println("You have " + list_count + " borrowed book/s");
                System.out.print("Select the book you want to return: ");
            }
        }

        public void borrow_successful(ArrayList<books> book, Random random, int borrowed_book, String name) {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║             BOOK BORROWED SUCCESSFULLY!            ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("  --You have borrowed: " + book.get(borrowed_book).getTitle() + "--");
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║  Your redeem code: {" + book.get(borrowed_book).getRedeem_code() + "}                        ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println();
        }

        public void borrow_failed() {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║     The book you selected is already borrowed.     ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println();
        }
    }
}
