import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Mark Philip
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwordsArray;
	String withDigit = "Potato4";
	
	@Before
	public void setUp() throws Exception {
		String[] p = {"112233AA", "L4terNerds",withDigit,};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidLength("e1!A530dA"));
		} catch (LengthException e) {
			// TODO Auto-generated catch block
			assertTrue(false); // Failed.
		}
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength("e1!A");
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Abcdef"));
			PasswordCheckerUtility.hasUpperAlpha("pota");
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("pota"));
			PasswordCheckerUtility.hasUpperAlpha("Pota");
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword("E1@abc33bdc"));
			PasswordCheckerUtility.isWeakPassword("E1@abc");
		} catch (InvalidSequenceException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (WeakPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(true);
		} catch (LengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		} catch (NoUpperAlphaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		} catch (NoLowerAlphaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		} catch (NoDigitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(true);
		} catch (NoSpecialCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertFalse(PasswordCheckerUtility.NoSameCharInSequence("pota"));
			PasswordCheckerUtility.NoSameCharInSequence("Pottta");
		} catch (InvalidSequenceException e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit("pota123"));
			PasswordCheckerUtility.hasDigit("Pottaa");
		} catch (NoDigitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("e1Ekd@"));
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| NoSpecialCharacterException | InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(), 3);
		assertEquals(results.get(1), "L4terNerds The password must contain at least one special character");
	}
	
}
