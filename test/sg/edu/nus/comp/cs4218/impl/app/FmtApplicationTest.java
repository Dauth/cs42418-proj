package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.FmtException;

public class FmtApplicationTest {
	private static FmtApplication fmtApplication;
	private static final String NEW_LINE = System.lineSeparator();
	private static ByteArrayOutputStream baos;
	private static ByteArrayInputStream bis;
	private static String fileToRead = "examples/sample.txt";

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fmtApplication = new FmtApplication();
	}

	@Before
	public void setUp() throws Exception {
		baos = new ByteArrayOutputStream();
		bis = new ByteArrayInputStream("This is a test string".getBytes());
	}

	/**
	 * Test if exception is raised when reading non existant file
	 *
	 * @throws FmtException
	 */
	@Test
	public void testFileReadWithNonExistantFile() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Could not read file");
		fmtApplication.checkIfFileIsReadable(Paths.get("examples/sampleNonExistent.txt"));
	}

	/**
	 * Test if exception is raised when reading directory insteaed of file
	 *
	 * @throws FmtException
	 */
	@Test
	public void testFileReadWithDirectory() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("This is a directory");
		fmtApplication.checkIfFileIsReadable(Paths.get("examples/"));
	}

	/**
	 * Test reading in from stdin
	 *
	 * @throws FmtException
	 */
	@Test
	public void testReadFromStdin() throws FmtException {
		assertEquals(fmtApplication.readFromStdin(bis), "This is a test string");
	}

	/**
	 * Test if exception is raised when stdin is null and no file specified
	 *
	 * @throws FmtException
	 */
	@Test
	public void testReadFromStdinNull() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Null pointer exception - stdin is not defined");
		assertEquals(fmtApplication.readFromStdin(null), "This is a test string");
	}

	/**
	 * Test if file checking for readablity is accurate
	 *
	 * @throws FmtException
	 */
	@Test
	public void testFileRead() throws FmtException {
		assertTrue(fmtApplication.checkIfFileIsReadable(Paths.get("examples/sample.txt")));
	}

	/**
	 * Test basic functionality and accuracy of text wrapping
	 *
	 * @throws FmtException
	 */
	@Test
	public final void testWrapText() throws FmtException {
		String[] arguments = { "examples/smallSampleLine.txt" };
		fmtApplication.run(arguments, null, baos);
		String resultString = new String(baos.toByteArray());
		String expectedString = "muse science yawn" + NEW_LINE;
		assertEquals(expectedString, resultString);
	}

	/**
	 * Test if wrapping is done without breaking of words in the event of a
	 * small wrap width
	 *
	 * @throws FmtException
	 */
	@Test
	public void testTooShortWrapWidth() throws FmtException {
		int wrapValue = 4;
		String wrappedText = fmtApplication.wrapText("The random string is a cat", wrapValue);
		String expectedString = "The" + NEW_LINE + "random" + NEW_LINE + "string" + NEW_LINE + "is" + NEW_LINE + "a"
				+ NEW_LINE + "cat" + NEW_LINE;
		assertEquals(expectedString, wrappedText);
	}

	/**
	 * Test if exception is raised when specified wrap width is negative
	 *
	 * @throws FmtException
	 */
	@Test
	public void testNegativeWrapWidth() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Wrap width should be at least 1");

		String[] arguments = { "-w", "-50", fileToRead };
		fmtApplication.run(arguments, null, baos);
	}

	/**
	 * Test if exception is raised if wrap width equals 0
	 *
	 * @throws FmtException
	 */
	@Test
	public void testZeroWrapWidth() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Wrap width should be at least 1");

		String[] arguments = { "-w", "0", fileToRead };
		fmtApplication.run(arguments, null, baos);
	}

	/**
	 * Test if exception is raised when specified wrap width is not a number
	 *
	 * @throws FmtException
	 */
	@Test
	public void testNonNumericWrapWidth() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Wrap width not a number");

		String[] arguments = { "-w", "ad3", fileToRead };
		fmtApplication.run(arguments, null, baos);
	}

	/**
	 * Test if exception is raised when flag to specify width is not -w
	 *
	 * @throws FmtException
	 */
	@Test
	public void testInvalidWrapWidthFlag() throws FmtException {
		exception.expect(FmtException.class);
		exception.expectMessage("Incorrect flag used to denote number of lines to print");

		String[] arguments = { "-n", "50", fileToRead };
		fmtApplication.run(arguments, null, baos);
	}

	/**
	 * Test if empty text can be wrapped without inaccuracies
	 *
	 * @throws FmtException
	 */
	@Test
	public void testEmptyText() throws FmtException {
		int wrapValue = 11;
		String wrappedText = fmtApplication.wrapText("", wrapValue);
		String expectedString = "";
		assertEquals(expectedString, wrappedText);
	}

	/**
	 * Test default wrap width
	 *
	 * @throws FmtException
	 */
	@Test
	public void testDefaultWrapWidth() throws FmtException {
		String[] arguments = { "examples/smallSample.txt" };
		fmtApplication.run(arguments, null, baos);
		String resultString = new String(baos.toByteArray());
		assertEquals("Indulgence announcing uncommonly met she Continuing two unpleasing terminated" + NEW_LINE
				+ "Now nusy busy apir" + NEW_LINE, resultString);
	}

	/**
	 * Test if existing new lines are removed before wrapping
	 *
	 * @throws FmtException
	 */
	@Test
	public void testRemovalOfNewLine() throws FmtException {
		int wrapValue = 100;
		String wrappedText = fmtApplication.wrapText(
				"The" + NEW_LINE + "random" + NEW_LINE + "String" + NEW_LINE + "is" + NEW_LINE + "tall", wrapValue);
		String expectedString = "The random String is tall" + NEW_LINE;
		assertEquals(expectedString, wrappedText);
	}

	/**
	 * Test if text can be read in from a text file
	 *
	 * @throws FmtException
	 */
	@Test
	public void testReadFromFile() throws FmtException {
		Path currentDir = Paths.get(Environment.currentDirectory);
		Path filePath = currentDir.resolve("examples/testRead.txt");
		String readString = fmtApplication.readFromFile(filePath);
		String expectedString = "Selon la prefecture, des engins explosifs avaient ete";
		assertEquals(expectedString, readString);
	}

	@After
	public void tearDown() throws Exception {
		baos = null;
		bis = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		fmtApplication = null;
	}
}

// References
// How do you assert that a certain exception is thrown in JUnit 4 tests?
// http://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
// Qn By : http://stackoverflow.com/users/1666/scdf
// Ans By : http://stackoverflow.com/users/21234/skaffman

// Java/ JUnit - AssertTrue vs AssertFalse
// http://stackoverflow.com/questions/3241105/java-junit-asserttrue-vs-assertfalse
// Qn By : http://stackoverflow.com/users/270847/thomas
// Ans By : http://stackoverflow.com/users/6198/matt-solnit
