import java.util.regex.Pattern;

/**
 * Login class for the Registration and Login feature (Part 1 of the PoE).
 *
 * Handles registering a user (with validation of username, password and
 * South African cell phone number) and logging that user back in.
 */
public class Login {

    // Stored details of the currently registered user.
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    private boolean registered = false;

    // Regex for a South African cell number: must start with the
    // international dialing code (+27) followed by 1-10 digits.
    // Pattern structure adapted from the tutorial on anchors/quantifiers at
    // https://www.regular-expressions.info/anchors.html
    private static final String CELL_PHONE_REGEX = "^\\+27[0-9]{1,10}$";

    /**
     * Username is correctly formatted if it contains an underscore and is
     * no more than five characters long in total.
     */
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Password must be at least 8 characters long and contain at least
     * one capital letter, one number and one special character.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
        return hasUpperCase && hasDigit && hasSpecialChar;
    }

    /**
     * Cell phone number must contain the South African international
     * dialing code (+27) followed by no more than ten digits.
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) {
            return false;
        }
        return Pattern.matches(CELL_PHONE_REGEX, cellPhoneNumber);
    }

    /**
     * Attempts to register a user, validating each field in turn.
     * Returns the message that should be shown to the user.
     */
    public String registerUser(String username, String password, String cellPhoneNumber,
                                String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, "
                    + "and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international "
                    + "code; please correct the number and try again.";
        }

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registered = true;

        return "User registered successfully.";
    }

    /**
     * Verifies that the entered username and password match the ones
     * stored at registration.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return registered
                && this.username.equals(enteredUsername)
                && this.password.equals(enteredPassword);
    }

    /**
     * Returns the message associated with a login attempt's outcome.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public boolean isRegistered() {
        return registered;
    }
}
