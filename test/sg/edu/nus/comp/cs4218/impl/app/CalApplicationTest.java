package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.CalException;

public class CalApplicationTest {
	private final String MONTHS[] = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private static final String START_SUN = "Su Mo Tu We Th Fr Sa";
	private static final String START_MON = "Mo Tu We Th Fr Sa Su";

	private CalApplication testCal;
	private int currentMonth, currentYear;

	@Before
	public void setUp() {
		testCal = new CalApplication();
		currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Tests whether the calendar app can print the calendar of the current
	 * month correctly.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCal() throws Exception {
		String[] arguments = new String[0];
		String[] lineOutputs = testCal.printCal(arguments).split(System.lineSeparator());
		int numberOfDays = new GregorianCalendar(currentYear, currentMonth, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
		assertTrue(lineOutputs[0].trim().equals(MONTHS[currentMonth] + " " + currentYear));
		assertTrue(lineOutputs[1].trim().equals(START_SUN));
		assertTrue(lineOutputs[2].trim().startsWith("1"));
		assertTrue(lineOutputs[lineOutputs.length - 1].trim().endsWith(Integer.toString(numberOfDays)));
	}

	/**
	 * Tests whether the calendar app can print the calendar of the current
	 * month with monday as the first day of the week.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCalWithMondayFirst() throws Exception {
		String[] arguments = new String[] { "-m" };
		String[] lineOutputs = testCal.printCalWithMondayFirst(arguments).split(System.lineSeparator());
		int numberOfDays = new GregorianCalendar(currentYear, currentMonth, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
		assertTrue(lineOutputs[0].trim().equals(MONTHS[currentMonth] + " " + currentYear));
		assertTrue(lineOutputs[1].trim().equals(START_MON));
		assertTrue(lineOutputs[2].trim().startsWith("1"));
		assertTrue(lineOutputs[lineOutputs.length - 1].trim().endsWith(Integer.toString(numberOfDays)));
	}

	/**
	 * Tests whether the calendar app can print the calendar of the given month
	 * and year.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCalForMonthYear() throws Exception {
		String mockMonth = "June";
		String mockYear = "1998";
		String[] arguments = { mockMonth, mockYear };
		String[] lineOutputs = testCal.printCalForMonthYear(arguments).split(System.lineSeparator());
		assertTrue(lineOutputs[0].trim().equals(mockMonth + " " + mockYear));
		assertTrue(lineOutputs[1].trim().equals(START_SUN));
		assertTrue(lineOutputs[2].trim().startsWith("1"));
		assertTrue(lineOutputs[lineOutputs.length - 1].trim().endsWith("30"));
	}

	/**
	 * Tests whether the calendar app can print the correct 3x4 grid of the year
	 * with all the included months required.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCalForYearGrid() throws Exception {
		String mockYear = "2007";
		String[] arguments = { mockYear };
		String rawOutput = testCal.printCalForYear(arguments);
		assertTrue(rawOutput.contains(mockYear));
		for (String month : MONTHS) {
			assertTrue(rawOutput.contains(month));
		}
		assertTrue(rawOutput.contains(START_SUN));
		assertTrue(!rawOutput.contains(START_MON));
	}

	/**
	 * Tests whether the calendar app can print the calendar of the given month
	 * and year with monday as the first day of the week.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCalForMonthYearMondayFirst() throws Exception {
		String mockMonth = "March";
		String mockYear = "2016";
		String[] arguments = { "-m", mockMonth, mockYear };
		String[] lineOutputs = testCal.printCalForMonthYearMondayFirst(arguments).split(System.lineSeparator());
		assertTrue(lineOutputs[0].trim().contains(MONTHS[2] + " " + 2016));
		assertTrue(lineOutputs[1].trim().contains(START_MON));
		assertTrue(lineOutputs[2].trim().startsWith("1"));
		assertTrue(lineOutputs[lineOutputs.length - 1].trim().endsWith("31"));
	}

	/**
	 * Tests whether the calendar app can print the calendar of the given year
	 * with monday as the first day of the week.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPrintCalForYearMondayFirst() throws Exception {
		String mockYear = "2011";
		String[] arguments = { "-m", mockYear };
		String rawOutput = testCal.printCalForYearMondayFirst(arguments);
		assertTrue(rawOutput.contains(mockYear));
		for (String month : MONTHS) {
			assertTrue(rawOutput.contains(month));
		}
		assertTrue(rawOutput.contains(START_MON));
		assertTrue(!rawOutput.contains(START_SUN));
	}

	/**
	 * Tests whether the calendar app can respond with an error message when
	 * given an invalid year.
	 *
	 * @throws Exception
	 */
	@Test(expected = CalException.class)
	public void testInvalidRunWithMonthOnly() throws Exception {
		String mockMonth = "March";
		String[] arguments = { mockMonth };
		ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();
		testCal.run(arguments, null, rawOutput);
	}

	/**
	 * Tests whether the calendar app can respond with an error message when
	 * given an invalid month but valid year.
	 *
	 * @throws Exception
	 */
	@Test(expected = CalException.class)
	public void testInvalidRunWithMonth() throws Exception {
		String mockMonth = "Test";
		String mockYear = "2011";
		String[] arguments = { mockMonth, mockYear };
		ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();
		testCal.run(arguments, null, rawOutput);
	}

	/**
	 * Tests whether the calendar app can respond with an error message when
	 * given an invalid month but valid year.
	 *
	 * @throws Exception
	 */
	@Test(expected = CalException.class)
	public void testInvalidFlag() throws Exception {
		String[] arguments = { "-M" };
		ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();
		testCal.run(arguments, null, rawOutput);
	}

	/**
	 * Tests whether the calendar app can respond with an error message when
	 * given an invalid month but valid year.
	 *
	 * @throws Exception
	 */
	@Test(expected = CalException.class)
	public void testInvalidFlagOrder() throws Exception {
		String mockYear = "2016";
		String[] arguments = { mockYear, "-m" };
		ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();
		testCal.run(arguments, null, rawOutput);
	}
}