package tests;

import data.FileUtils;

public class PasswordResetterTest {
    public static void main(String[] args) {
        PasswordResetter passwordResetter = new PasswordResetter();

        // Test 1: Successful password reset
        String result = passwordResetter.resetPassword("123", "newPassword", "newPassword");
        System.out.println("Expected: Success, Actual: " + result);
        System.out.println("Expected newPassword, Actual: " + FileUtils.readFile(PasswordResetter.PASSWORD_FILE));

        // Test 2: Incorrect answer
        result = passwordResetter.resetPassword("wrongAnswer", "newPassword", "newPassword");
        System.out.println("Expected: Incorrect answer, Actual: " + result);
    }
}