package com.driver;

import java.util.HashSet;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){

        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        if (this.password.equals(oldPassword) == false) {
            return;
        }

        // 1. It contains at least 8 characters
        if (newPassword.length() < 8) {
            return;
        }

        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        HashSet<Character> hashSet = new HashSet<>();

        for (int i = 0; i < newPassword.length(); i++) {
            hashSet.add(newPassword.charAt(i));
        }

        boolean isUpperCaseLetterInIt = containsUppercaseLetter(hashSet);
        boolean isLowerCaseLetterInIt = containsLowercaseLetter(hashSet);
        boolean isDigitInIt = containsDigit(hashSet);
        boolean isOneSpecialCharacterInIt = containsOneSpecialCharacter(hashSet);

        boolean isValid = isUpperCaseLetterInIt && isLowerCaseLetterInIt && isDigitInIt && isOneSpecialCharacterInIt;

        if (isValid) {
            this.password = newPassword;
        }

    }

    private boolean containsOneSpecialCharacter(HashSet<Character> hashSet) {

        for (char ch : hashSet) {

            if (ch >= 32 && ch <= 47) {
                return true;
            }

            if (ch >= 58 && ch <= 64) {
                return true;
            }

            if (ch >= 91 && ch <= 96) {
                return true;
            }

            if (ch >= 123 && ch <= 126) {
                return true;
            }

            if (ch >= 128 && ch <= 255) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(HashSet<Character> hashSet) {

        for (char ch : hashSet) {

            if (ch >= 48 && ch <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLowercaseLetter(HashSet<Character> hashSet) {

        for (char ch : hashSet) {

            if (ch >= 97 && ch <= 122) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUppercaseLetter(HashSet<Character> hashSet) {

        for (char ch : hashSet) {

            if (ch >= 65 && ch <= 90) {
                return true;
            }
        }
        return false;
    }

}
