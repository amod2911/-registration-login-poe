import java.util.Scanner;

/**
 * Console application entry point for the Registration and Login PoE task.
 * No GUI / JOptionPane is used, per the assignment instructions.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        // --- Registration ---
        String registrationResult;
        do {
            System.out.println("=== Register a new account ===");

            System.out.print("Enter a username (must contain '_' and be no more than 5 characters): ");
            String username = scanner.nextLine();

            System.out.print("Enter a password (8+ chars, capital letter, number, special character): ");
            String password = scanner.nextLine();

            System.out.print("Enter your South African cell number (e.g. +27831234567): ");
            String cellPhoneNumber = scanner.nextLine();

            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();

            registrationResult = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
            System.out.println(registrationResult);
            System.out.println();

        } while (!login.isRegistered());

        // --- Login ---
        System.out.println("=== Log in ===");
        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loginSuccessful));

        scanner.close();
    }
}
