import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/**
 * PasswordCheckerUtility go crazy frfr
 * @author Mark Philip
 *
 */

public class PasswordCheckerUtility {

	/**
     * Compare equality of two passwords
     *
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @throws UnmatchedException - thrown if not same (case sensitive)
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!password.equals(passwordConfirm)) {
            throw new UnmatchedException("Passwords do not match");
        }
    }

    /**
     * Compare equality of two passwords.
     *
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @return true if both same (case sensitive), false otherwise
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    /**
     * Checks if passwords in the list are invalid.
     *
     * @param password - password string to be checked for length
     * @return true if meets minimum length requirement
     * @throws LengthException - thrown if does not meet minimum length requirement
     */

    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }
        return true;
    }
    
    /**
     * Checks the password alpha character requirement - Password must contain an uppercase alpha character
     *
     * @param password - password string to be checked for alpha character requirement
     * @return true if meet alpha character requirement
     * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        throw new NoUpperAlphaException("The password must contain at least one uppercase alphanumeric character");
    }
    
    /**
     * Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
     *
     * @param password - password string to be checked for lowercase requirement
     * @return true if meets lowercase requirement
     * @throws NoLowerAlphaException - thrown if does not meet lowercase requirement
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
    }
    
    /**
     * Checks the password Digit requirement - Password must contain a numeric character
     *
     * @param password - password string to be checked for Digit requirement
     * @return true if meet Digit requirement
     * @throws NoDigitException - thrown if does not meet Digit requirement
     */
    public static boolean hasDigit(String password) throws NoDigitException {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        throw new NoDigitException("Password must contain at a numerical digit");
    }
    
    /**
     * Checks the password SpecialCharacter requirement - Password must contain a Special Character
     *
     * @param password - password string to be checked for SpecialCharacter requirement
     * @return true if meets SpecialCharacter requirement
     * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        throw new NoSpecialCharacterException("The password must contain at least one special character");
    }
    
    /**
     * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
     *
     * @param password - password string to be checked for Sequence requirement
     * @return false if does NOT meet Sequence requirement
     * @throws InvalidSequenceException - thrown if meets Sequence requirement
     */
    public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                throw new InvalidSequenceException("Password contains more than 2 of the same character in sequence");
            }
        }
        return false;
    }

    /**
     * Return true if valid password (follows all the following rules), returns false if an invalid password 1. At least 6 characters long - 2. At least 1 numeric character- 3. At least 1 uppercase alphabetic character - 4. At least 1 lowercase alphabetic character - 5. At least 1 special character - 6. No more than 2 of the same character in a sequence - Hello@123 – OK AAAbb@123 – not OK Aaabb@123 – OK
     *
     * @param password - - string to be checked for validity
     * @return true if valid password (follows all rules from above), false if an invalid password
     * @throws LengthException - thrown if length is less than 6 characters
     * @throws NoUpperAlphaException - thrown if no uppercase alphabetic
     * @throws NoLowerAlphaException - thrown if no lowercase alphabetic
     * @throws NoDigitException - thrown if no digit
     * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
     * @throws InvalidSequenceException - thrown if more than 2 of same character.
     */
    public static boolean isValidPassword(String password) 
    		throws LengthException, 
    		NoUpperAlphaException, 
    		NoLowerAlphaException,
    		NoDigitException, 
    		NoSpecialCharacterException, 
    		InvalidSequenceException {
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		NoSameCharInSequence(password);
		return true;
    }
    
    /**
     * checks if the password contains 6 to 9 characters
     *
     * @param password - password string to be checked for
     * @return true if password contains 6 to 9 characters, false otherwise
     */
    
    public static boolean hasBetweenSixAndNineChars(String password) {
        return password.length() >= 6 && password.length() <= 9;
    }

    /**
     * Checks if password is VALID and the length is NOT between 6-9 characters
     *
     * @param password - string to be checked if weak password
     * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
     * @throws WeakPasswordException - if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        if (isValidPassword(password) && hasBetweenSixAndNineChars(password)) {
            throw new WeakPasswordException("Weak Password: Length is between 6-9 characters");
        }
        return false;
    }
    
    /**
     * This method will accept an ArrayList of passwords as the parameter and return an ArrayList with the status of any invalid passwords (weak passwords are not considered invalid). The ArrayList of invalid passwords will be of the following format: password BLANK message of the exception thrown
     *
     * @param passwords - list of passwords
     * @return ArrayList of invalid passwords in the correct format
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                if (!isValidPassword(password)) {
                    invalidPasswords.add(password);
                }
            } catch (Exception e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }
}

// Exceptions

class UnmatchedException extends Exception {
    public UnmatchedException(String message) {
        super(message);
    }
}

class LengthException extends Exception {
    public LengthException(String message) {
        super(message);
    }
}

class NoUpperAlphaException extends Exception {
    public NoUpperAlphaException(String message) {
        super(message);
    }
}

class NoLowerAlphaException extends Exception {
    public NoLowerAlphaException(String message) {
        super(message);
    }
}

class NoDigitException extends Exception {
    public NoDigitException(String message) {
        super(message);
    }
}

class NoSpecialCharacterException extends Exception {
    public NoSpecialCharacterException(String message) {
        super(message);
    }
}

class InvalidSequenceException extends Exception {
    public InvalidSequenceException(String message) {
        super(message);
    }
}

class WeakPasswordException extends Exception {
    public WeakPasswordException(String message) {
        super(message);
    }
}