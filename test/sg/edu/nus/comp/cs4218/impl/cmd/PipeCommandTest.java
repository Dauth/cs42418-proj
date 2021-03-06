package sg.edu.nus.comp.cs4218.impl.cmd;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PipeCommandException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class PipeCommandTest {
	private static ShellImpl shell;
	static final String NEW_LINE = System.lineSeparator();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shell = new ShellImpl();
	}

	/**
	 * Test the pipe command with multiple commands.
	 */
	@Test
	public void testPipeMultipleCommands() {
		String cmdLine = "head -n 20 examples/file3.txt | sort | head -n 7 | sort -n";
		String expectedResult = "1" + NEW_LINE + "10" + NEW_LINE + "11" + NEW_LINE + "12" + NEW_LINE + "13" + NEW_LINE
				+ "14" + NEW_LINE + "15" + NEW_LINE;
		String actualResult = shell.pipeMultipleCommands(cmdLine.split(" "));
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Test the pipe command with two commands.
	 */
	@Test
	public void testPipeTwoCommands() {
		String cmdLine = "sort examples/file3.txt | head -n 5";
		String expectedResult = "1" + NEW_LINE + "10" + NEW_LINE + "11" + NEW_LINE + "12" + NEW_LINE + "13" + NEW_LINE;
		String actualResult = shell.pipeTwoCommands(cmdLine.split(" "));
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Test the pipe command with an exception occurring in the last call
	 * command.
	 */
	@Test
	public void testPipeWithExceptionBehind() {
		String expectedResult = "pipe: exception detected for one of the call commands";
		String cmdLine = "sort examples/file3.txt | head -j 2";
		String actualResult = shell.pipeWithException(cmdLine.split(" "));
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Test the pipe command with an exception occurring in the front call
	 * command.
	 */
	@Test
	public void testPipeWithExceptionInfront() {
		String expectedResult = "pipe: exception detected for one of the call commands";
		String cmdLine = "sort -j examples/file3.txt | head";
		String actualResult = shell.pipeWithException(cmdLine.split(" "));
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Test the pipe command starting from the shell.
	 * 
	 * @throws ShellException
	 * @throws AbstractApplicationException
	 */
	@Test
	public void testFromShell() throws ShellException, AbstractApplicationException {
		String temp = "sort -n examples/file3.txt | head -n 5";
		String expected = "1" + NEW_LINE + "2" + NEW_LINE + "3" + NEW_LINE + "4" + NEW_LINE + "5" + NEW_LINE;
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		shell.parseAndEvaluate(temp, stdout);
		assertEquals(expected, stdout.toString());
	}

	/**
	 * Test the pipe command starting from the shell with an exception occurring
	 * at the last call command.
	 * 
	 * @throws ShellException
	 * @throws AbstractApplicationException
	 */
	@Test(expected = PipeCommandException.class)
	public void testFromShellWithExceptionInFront() throws ShellException, AbstractApplicationException {
		String temp = "sort -j examples/file3.txt | head -n 5";
		String expected = "1" + NEW_LINE + "2" + NEW_LINE + "3" + NEW_LINE + "4" + NEW_LINE + "5" + NEW_LINE;
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		shell.parseAndEvaluate(temp, stdout);
	}

	/**
	 * Test the pipe command starting from the shell with an exception occurring
	 * at the front call command.
	 * 
	 * @throws ShellException
	 * @throws AbstractApplicationException
	 */
	@Test(expected = PipeCommandException.class)
	public void testFromShellWithExceptionBehind() throws ShellException, AbstractApplicationException {
		String temp = "sort -n examples/file3.txt | head -j 5";
		String expected = "1" + NEW_LINE + "2" + NEW_LINE + "3" + NEW_LINE + "4" + NEW_LINE + "5" + NEW_LINE;
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		shell.parseAndEvaluate(temp, stdout);
	}

	/**
	 * Tests the pipe command with missing arguments to the command.
	 *
	 * @throws ShellException
	 * @throws AbstractApplicationException
	 */
	@Test(expected = PipeCommandException.class)
	public void testMissingPipeOperator() throws ShellException, AbstractApplicationException {
		String temp = "echo abc | echo 123 |";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		shell.parseAndEvaluate(temp, stdout);
	}

	/**
	 * Tests the pipe command with invalid pipe arguments.
	 *
	 * @throws ShellException
	 * @throws AbstractApplicationException
	 */
	@Test(expected = PipeCommandException.class)
	public void testDoublePipeOperator() throws ShellException, AbstractApplicationException {
		String temp = "echo abc || echo 123";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		shell.parseAndEvaluate(temp, stdout);
	}
}
