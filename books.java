
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class books {

    static ui main_ui = new ui();
    static ui.userUi userUi = main_ui.new userUi();

    private final String title;
    private String username;
    private boolean availability;
    private String redeem_code;

    books(String username, String title, boolean availability, String redeem_code) {
        this.username = username;
        this.title = title;
        this.availability = availability;
    }

    public static void book_list(ArrayList<books> book, String username, String redeem_code, String title, boolean availability){
        book.add(new books(username, "Game of thrones", true, redeem_code));
        book.add(new books(username, "American Prometheus", true, redeem_code));
        book.add(new books(username, "Harry Potter", true, redeem_code));
    }

    public String getTitle(){
        return this.title;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean getAvailability(){
        return this.availability;
    }

    public String getRedeem_code(){
        return this.redeem_code;
    }

    public void borrow(String name, String redeem_code) {
        this.availability = false;
        this.username = name;
        this.redeem_code = redeem_code;
    }

    public void returned() {
        this.availability = true;
        this.username = null;
    }

    public static void add_book(ArrayList<books> book, Scanner scanner, boolean availability, String title, int number_added_books, String username) {

        for (int i = 0; i < number_added_books; i++) {
            System.out.println(i + 1 + ". Enter the necessary parameters");
            System.out.println("-Title: ");
            title = scanner.nextLine();
            System.out.println("-Availability of book (AVAILABLE or BORROWED): ");
            String available_status = scanner.nextLine();
            available_status = available_status.toUpperCase();

            switch (available_status) {
                case "AVAILABLE" -> {
                    availability = true;
                    username = null;
                }
                case "BORROWED" -> {
                    availability = false;
                    System.out.println("-Name of borrower ('null' if N/A): ");
                    String borrower_name = scanner.nextLine();
                    borrower_name = borrower_name.toLowerCase();
                    username = borrower_name;
                }
                default -> System.out.println("Enter valid input");
            }

        }
        book.add(new books(username, title, availability, null));
    }

    public void remove_book(Scanner scanner, ArrayList<books> book, int remove_selection) {

        if (!this.availability) {
            System.out.println("Selected book is borrowed, wait for reader to return or set book availability to 'false' ");
            System.out.println("1. Return to menu  ||  2. Force remove");
            int option_remove = scanner.nextInt();
            scanner.nextLine();

            switch (option_remove) {
                case 1 -> {}
                case 2 -> book.remove(remove_selection);
                default -> throw new AssertionError();
            }
        } else {
            book.remove(remove_selection);
        }
    }

    public void borrow_book(ArrayList<books> book, int borrowed_book, Random random, String name) {
        if (this.availability == true) {
            this.borrow(name, redeem_code(random));
            userUi.borrow_successful(book, random, borrowed_book, name);
            
        } else {
            userUi.borrow_failed();
            this.availability = false;
        }
    }

    public static void return_book(ArrayList<books> book, String name, Scanner scanner) {
        int list_count = 0; // Set to 0 if there are no borrowed books
        ArrayList<books> borrowed_list = new ArrayList<>();

        userUi.return_menu(book, name, list_count, scanner);

        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).availability == false && book.get(i).username.equals(name)) {
                list_count++;
                borrowed_list.add(book.get(i));
            }
        }
        if (list_count != 0) {
            int returned_book = scanner.nextInt() - 1;
            scanner.nextLine(); 

            // if (returned_book == 0) {
            //     break;
            // }

            System.out.println(borrowed_list.get(returned_book).title);
            for (int i = 0; i < book.size(); i++) {
                if (book.get(i).title.equals(borrowed_list.get(returned_book).title)) {
                    book.get(i).returned();
                }
            }
            System.out.println(" ");
            System.out.println("║ " + borrowed_list.get(returned_book).title + " has been returned ║");
        }
    }

    private String redeem_code(Random random) {
        char[] lower_case = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upper_case = "ABCDEFGHIJKLMNOPQRESTUVWXYZ".toCharArray();
        int random_number = random.nextInt(10000);
        String number_code = String.valueOf(random_number);
        char char1 = lower_case[random.nextInt(lower_case.length)];
        char char2 = upper_case[random.nextInt(upper_case.length)];
        String random_code = "" + char1 + char2 + number_code;
        return random_code;
    }

    public static String lib_Auth(){
        String password = "111AAA";
        return password;
    }
}
