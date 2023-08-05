package tests;

import data.FileUtils;

public class PasswordManager {

    public static final String PASSWORD_FILE = "password.txt";
    public static final String SECURITY_QUESTION_FILE = "question.txt";
    public static final String SECURITY_ANSWER_FILE = "answer.txt";

    public String changePassword(String currentPasswordInput,
                                 String newPassword,
                                 String confirmNewPassword,
                                 String newSecurityQuestion,
                                 String newSecurityAnswer) {
        String currentPassword = FileUtils.readFile(PASSWORD_FILE);
        if (currentPasswordInput.equals(currentPassword) &&
                newPassword.equals(confirmNewPassword)) {
            FileUtils.writeFile(PASSWORD_FILE, newPassword);
            FileUtils.writeFile(SECURITY_QUESTION_FILE, newSecurityQuestion);
            FileUtils.writeFile(SECURITY_ANSWER_FILE, newSecurityAnswer);
            return "Success";
        } else if (!currentPassword.equals(currentPasswordInput)) {
            return "Incorrect password";
        } else {
            return "Passwords don't match";
        }
    }
}

