

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("this test will always pass",305023234,4151,"AHAHAHAHA","CAUSE I AM COOL");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("CMSC208",30504,4,"SC450","A Real teacher name");
		dataMgr.add("CMSC206",30503,4,"SC450","I Like Drugs");
		dataMgr.add("CMSC204",30559,4,"SC450","JK, don't report me");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:CMSC204 CRN:30559 Credits:4 Instructor:JK, don't report me Room:SC450");
	 	assertEquals(list.get(1),"\nCourse:CMSC206 CRN:30503 Credits:4 Instructor:I Like Drugs Room:SC450");
		assertEquals(list.get(2),"\nCourse:CMSC208 CRN:30504 Credits:4 Instructor:A Real teacher name Room:SC450");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 30504 4 AC850 Banjo");
			inFile.print("CMSC204 30503 4 BC420 Kazooie");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("CMSC203",dataMgr.get(30504).getID());
			assertEquals("CMSC204",dataMgr.get(30503).getID());
			assertEquals("BC420",dataMgr.get(30503).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
