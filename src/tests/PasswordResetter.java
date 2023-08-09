package tests;

import data.FileUtils;

public class PasswordResetter {
    public static final String PASSWORD_FILE = "password.txt";
    public static final String SECURITY_QUESTION_FILE = "question.txt";
    public static final String SECURITY_ANSWER_FILE = "answer.txt";

    public String resetPassword(String providedAnswer,
                                String newPassword,
                                String confirmNewPassword) {
        String securityAnswer = FileUtils.readFile(SECURITY_ANSWER_FILE);
        if (providedAnswer.equals(securityAnswer) && newPassword.equals(confirmNewPassword)) {
            FileUtils.writeFile(PASSWORD_FILE, newPassword);
            return "Success";
        } else {
            return "Incorrect answer";
        }
    }
}

