import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    // ---------- checkUserName ----------

    @Test
    void testUserNameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testUserNameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    void testCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------- registerUser ----------

    @Test
    void testRegisterUserInvalidUsername() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    void testRegisterUserInvalidPassword() {
        String result = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertEquals("Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, "
                + "and a special character.", result);
    }

    @Test
    void testRegisterUserInvalidCellPhoneNumber() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        assertEquals("Cell number is incorrectly formatted or does not contain an international "
                + "code; please correct the number and try again.", result);
    }

    @Test
    void testRegisterUserSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("User registered successfully.", result);
        assertTrue(login.isRegistered());
    }

    // ---------- loginUser / returnLoginStatus ----------

    @Test
    void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
        assertEquals("Welcome Kyle, Smith it is great to see you again.", login.returnLoginStatus(result));
    }

    @Test
    void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean result = login.loginUser("kyl_1", "wrongPassword1!");
        assertFalse(result);
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(result));
    }
    @Test
void testUserNameExactlyFiveCharsWithUnderscore() {
    assertTrue(login.checkUserName("ky_12")); // exactly 5 chars, has underscore
}
    @Test
    void testUserNameNoUnderscoreButShortEnough() {
        assertFalse(login.checkUserName("kyle")); // short but missing underscore
    }
    @Test
    void testPasswordMissingSpecialCharacter() {
        assertFalse(login.checkPasswordComplexity("Password99"));
    }
}
