package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.app.Sort;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.misc.SortHelper;

public class SortApplication implements Sort {

	public static final int MAX_LENGTH = 2;
	private static final int ONE = 1;
	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final String CHARSET_UTF_8 = "UTF-8";

	/**
	 * Returns an ordered list of lines containing only simple letters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortStringsSimple(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(ONE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing only capital letters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortStringsCapital(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(ONE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing only numbers in natural order
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortNumbers(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(ONE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing only numbers in asc order
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortNumbersWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(ONE, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing only special characters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(ONE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSimpleCapital(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple letters and numbers
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSimpleNumbers(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple letters and numbers
	 * with numbers in asc order
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSimpleNumbersWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple letters and special
	 * characters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSimpleSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing capital letters and numbers
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortCapitalNumbers(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing capital letters and numbers
	 * in ascending order
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortCapitalNumberswithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing capital letters and special
	 * character
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortCapitalSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing numbers and special
	 * characters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortNumbersSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing numbers and special
	 * characters
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortNumbersSpecialCharsWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(TWO, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters
	 * and numbers
	 * 
	 * @throws SortException
	 */
	@Override
	public List<String> sortSimpleCapitalNumber(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters
	 * and numbers
	 */
	@Override
	public List<String> sortSimpleCapitalNumberWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters
	 * and special characters
	 */
	@Override
	public List<String> sortSimpleCapitalSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple letters, numbers and
	 * special characters
	 */
	@Override
	public List<String> sortSimpleNumbersSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple letters, numbers and
	 * special characters in asc order
	 */
	@Override
	public List<String> sortSimpleNumbersSpecialCharsWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing capital letters, numbers and
	 * special characters
	 */
	@Override
	public List<String> sortCapitalNumbersSpecialChars(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing capital letters, numbers and
	 * special characters
	 */
	@Override
	public List<String> sortCapitalNumbersSpecialCharsWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(THREE, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters,
	 * numbers and special characters
	 */
	@Override
	public List<String> sortAll(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(FOUR, toSort));
		return SortHelper.sortHelper(extractedList);
	}

	/**
	 * Returns an ordered list of lines containing simple and capital letters,
	 * numbers and special characters in asc order
	 */
	@Override
	public List<String> sortAllWithNumFlagOn(String... toSort) throws SortException {
		List<String> extractedList = new ArrayList<String>(SortHelper.separateBasedOnType(FOUR, toSort));
		return SortHelper.sortHelperWithNumFlag(extractedList);
	}

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws SortException {
		String[] toSort = SortHelper.sortProcess(args, stdin);
		stdoutSortedArray(stdout, toSort);
	}

	/**
	 * write the sorted array to the outstream
	 * 
	 * @param outputstream
	 *            stream to write out
	 * @param string
	 *            array sorted array
	 * @throws SortException
	 */
	private void stdoutSortedArray(OutputStream stdout, String... toSort) throws SortException {
		if (stdout == null) {
			throw new SortException("stdout is not present");
		}
		for (int i = 0; i < toSort.length; i++) {
			try {
				stdout.write(toSort[i].getBytes(CHARSET_UTF_8));
				stdout.write(System.lineSeparator().getBytes("UTF-8"));
			} catch (IOException e) {
				throw new SortException("Could not write to output stream", e);
			}
		}
	}

	/**
	 * Read from stdin and write to a string array
	 * 
	 * @param stdin
	 *            An input Stream. Reading from stdin and not a file
	 * @throws SortException
	 *             If stdin. I/O exceptions caught when reading and writing from
	 *             input and output streams.
	 */

	public static String[] readFromStdinAndWriteToStringArray(InputStream stdin) throws SortException {
		List<String> resultList = new ArrayList<String>();
		if (stdin == null) {
			throw new SortException("Null Pointer Exception");
		}
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(stdin));
		String input = "";
		try {
			while ((input = buffReader.readLine()) != null) {
				resultList.add(input);
			}
		} catch (Exception e) {
			throw new SortException("Exception caught", e);
		}
		return resultList.toArray(new String[resultList.size()]);
	}

	/**
	 * Catch the missing 'n' flag when the argument length is of 2
	 * 
	 * @param args
	 *            arguments present in the command
	 * @param currentDir
	 *            path where the source file resides
	 * @param filePosition
	 *            position of the filename in the args
	 * @return void
	 * @throws SortException
	 *             If the 'n' flag is missing in the command format
	 */
	public static String[] getFileContents(String[] args, Path currentDir, int filePosition) throws SortException {
		ArrayList<Path> filePathList = new ArrayList<Path>();
		for (int i = filePosition; i < args.length; i++) {
			Path filePath = currentDir.resolve(args[i]);
			catchIfFileIsReadableException(filePath);
			filePathList.add(filePath);
		}
		return readFromFileAndWriteToStringArray(filePathList);
	}

	/**
	 * Checks if the '-n' flag is present in the command format
	 * 
	 * @param args
	 *            arguments present in the command
	 * @return True if the 'n' flag is present.
	 * @throws SortException
	 *             If the file is not readable
	 */
	public static boolean isNumberCommandFormat(String... args) {
		return args[ZERO].equals("-n");
	}

	/**
	 * Checks if a file is readable.
	 * 
	 * @param filePath
	 *            The path to the file
	 * @return True if the file is readable.
	 * @throws SortException
	 *             If the file is not readable
	 */
	private static void catchIfFileIsReadableException(Path filePath) throws SortException {
		if (!Files.exists(filePath) && !Files.isReadable(filePath)) {
			throw new SortException("Could not read file");
		}
	}

	/**
	 * Read from file and return a string array
	 * 
	 * @param filePath
	 *            A Path. Read file from the file path given
	 * @throws SortException
	 *             Exceptions caught when reading and writing from input file.
	 */
	private static String[] readFromFileAndWriteToStringArray(ArrayList<Path> filePathList) throws SortException {
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < filePathList.size(); i++) {
			try {
				FileInputStream fileInStream = new FileInputStream(filePathList.get(i).toString());
				BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileInStream));

				String input = "";
				while ((input = buffReader.readLine()) != null) {
					arrayList.add(input);
				}
				buffReader.close();

			} catch (IOException e) {
				throw new SortException("IOException", e);
			}
		}

		return arrayList.toArray(new String[arrayList.size()]);
	}
}
