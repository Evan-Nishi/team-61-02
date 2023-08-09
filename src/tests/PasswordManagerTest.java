package tests;

import data.FileUtils;

public class PasswordManagerTest {
    public static void main(String[] args) {
        PasswordManager passwordManager = new PasswordManager();

        // Test 1: Successful password change
        String result = passwordManager.changePassword("currentPassword", "newPassword", "newPassword", "newQuestion", "newAnswer");
        System.out.println("Expected: Success, Actual: " + result);
        System.out.println("Expected newPassword, Actual: " + FileUtils.readFile(PasswordManager.PASSWORD_FILE));
        System.out.println("Expected newQuestion, Actual: " + FileUtils.readFile(PasswordManager.SECURITY_QUESTION_FILE));
        System.out.println("Expected newAnswer, Actual: " + FileUtils.readFile(PasswordManager.SECURITY_ANSWER_FILE));

        // Resetting for the next test
        FileUtils.writeFile(PasswordManager.PASSWORD_FILE, "currentPassword");
        FileUtils.writeFile(PasswordManager.SECURITY_QUESTION_FILE, "currentQuestion");
        FileUtils.writeFile(PasswordManager.SECURITY_ANSWER_FILE, "currentAnswer");

        // Test 2: Incorrect current password
        result = passwordManager.changePassword("wrongPassword", "newPassword", "newPassword", "newQuestion", "newAnswer");
        System.out.println("Expected: Incorrect password, Actual: " + result);
    }
}

