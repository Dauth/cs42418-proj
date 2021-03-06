package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.CommException;

public class CommApplicationTestBug {

	private CommApplication commApplication;
	private InputStream stdin;
	private OutputStream stdout;

	private static final String FILE1 = "comm_file1.txt";
	private static final String FILE2 = "comm_file2.txt";
	private static final Path PATH_FILE1 = Paths.get(FILE1);
	private static final Path PATH_FILE2 = Paths.get(FILE2);
	private static final String FILE1_CONTENT = "line1\nline2\nline3\nline8";
	private static final String FILE2_CONTENT = "line1\nline3\nline4\nline8";
	private static final String COMM_FILE1_FILE2 = "\t\tline1" + System.lineSeparator() + "line2"
			+ System.lineSeparator() + CommApplication.DOUBLE_TAB + "line3" + System.lineSeparator()
			+ CommApplication.SINGLE_TAB + "line4" + System.lineSeparator() + "\t\tline8" + System.lineSeparator();

	private static final String EMPTY_FILE1 = "comm_emptyfile1.txt";
	private static final String EMPTY_FILE2 = "comm_emptyfile2.txt";
	private static final Path PATH_EMTPY_FILE1 = Paths.get(EMPTY_FILE1);
	private static final Path PATH_EMTPY_FILE2 = Paths.get(EMPTY_FILE2);
	private static final String NEW_LINE = System.lineSeparator();
	private static final String TAB_LINE = "\t";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Files.deleteIfExists(PATH_FILE1);
		Files.deleteIfExists(PATH_FILE2);
		Files.deleteIfExists(PATH_EMTPY_FILE1);
		Files.deleteIfExists(PATH_EMTPY_FILE2);

		Files.createFile(PATH_FILE1);
		Files.createFile(PATH_FILE2);
		FileWriter fileWriter = new FileWriter(new File(PATH_FILE1.toString()));
		fileWriter.write(FILE1_CONTENT);
		fileWriter.flush();
		fileWriter.close();
		fileWriter = new FileWriter(new File(PATH_FILE2.toString()));
		fileWriter.write(FILE2_CONTENT);
		fileWriter.flush();
		fileWriter.close();
		Files.createFile(PATH_EMTPY_FILE1);
		Files.createFile(PATH_EMTPY_FILE2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Files.deleteIfExists(PATH_FILE1);
		Files.deleteIfExists(PATH_FILE2);
		Files.deleteIfExists(PATH_EMTPY_FILE1);
		Files.deleteIfExists(PATH_EMTPY_FILE2);
	}

	@Before
	public void setUp() throws Exception {
		commApplication = new CommApplication();
		stdin = null;
		stdout = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		commApplication = null;
		stdin = null;
		stdout = null;
	}

	/**
	 * Bug report:
	 *
	 * Exception thrown which requires sorted input to be fed. Note an input has
	 * to be sorted before it can be used in comm is not written anywhere in the
	 * project description for Comm Application. It only states to compare each
	 * line in order of occurrence.
	 * 
	 * @throws CommException
	 */
	@Test
	public void test1() throws CommException {
		String[] args = { "comm3.txt", "comm4.txt" };
		commApplication.run(args, null, stdout);
		String expected = TAB_LINE + "\"" + TAB_LINE + NEW_LINE + TAB_LINE + ">" + TAB_LINE + NEW_LINE + TAB_LINE + "M"
				+ TAB_LINE + NEW_LINE + TAB_LINE + "9" + TAB_LINE + NEW_LINE + "o" + TAB_LINE + TAB_LINE + NEW_LINE
				+ "3" + TAB_LINE + TAB_LINE + NEW_LINE + "{" + TAB_LINE + TAB_LINE;
		System.out.println(stdout.toString());
		assertEquals(expected, stdout.toString());

	}

	/**
	 * Bug report:
	 *
	 * There is a bug with the getLines method in CommApplication despite
	 * getting the correct contents in the files. It chops off the first char of
	 * the word which results in an improper comparing order file1 = bear\nzebra
	 * file2 = apple\nbear\nzebra
	 * 
	 * @throws CommException
	 */
	@Test
	public void test2() throws CommException {
		String[] args = { "comm5Sorted.txt", "comm6Sorted.txt" };
		commApplication.run(args, null, stdout);
		String expected = TAB_LINE + "apple" + TAB_LINE + NEW_LINE + TAB_LINE + TAB_LINE + "bear" + NEW_LINE + TAB_LINE
				+ TAB_LINE + "zebra" + NEW_LINE;
		assertEquals(expected, stdout.toString());

	}

}
