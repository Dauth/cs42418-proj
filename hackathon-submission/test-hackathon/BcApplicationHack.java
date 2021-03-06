import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.BcException;
import sg.edu.nus.comp.cs4218.impl.app.BcApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BcApplicationHack {
    private BcApplication bcApp;
    private ByteArrayInputStream inStream;
    private ByteArrayOutputStream outStream;

    @Before
    public void setUp() throws Exception {
        bcApp = new BcApplication();
        inStream = new ByteArrayInputStream(new byte[1]);
        outStream = new ByteArrayOutputStream();
    }

    /**
     * Fails to throw an exception but returned 50 instead as ((5)0) is an invalid expression based on specification.
     *
     * This bug does not conform to "if required command line arguments are not provided or arguments are wrong or
     * inconsistent, the application throws an exception as well." Ref specs page 10 section application specification
     * paragraph 1.
     *
     */
    @Test(expected = BcException.class)
    public void testBracketsWithoutOperators() throws Exception {
        String[] params = { "((5)0)" };
        bcApp.run(params, inStream, outStream);
        fail("Should have thrown exception but did not as it is not a valid expression.");
    }

    /**
     * Fails to account for a operator precedence and parsing with unary minus, multiply, division.
     *
     * This bug does not conform to " Typical rules of precedence and associativity apply." Ref specs page 12 section
     * bc paragraph 2.
     *
     */
    @Test
    public void testUnaryMinusExpression() throws Exception {
        String[] params = { "5/-1*-2" };
        bcApp.run(params, inStream, outStream);
        assertEquals("10", outStream.toString());
    }

    /**
     * Fails to return 1. In standard logical AND operator convention, only 0 stands for false while any another value
     * except 0 represents true. Similar for OR.
     *
     * This bug despite not mentioned in the specification should be clarified by the comments of the team on how
     * the operators work. It is stated in the announcement that "You can only defend formally if you have evidence,
     * e.g., by saying so in the documentation or with a test case that exercises this supposedly buggy functionality
     * but passes.". As such we use the standard convention used by multiple languages and official UNIX Bc specification
     * to evaluate your operators. If we do not do so then your operators correctness cannot be tested which questions
     * the purpose of testing Bc.
     *
     */
    @Test
    public void testAndNegativeIntegers() throws Exception {
        String[] params = { "-1 && -1" };
        bcApp.run(params, inStream, outStream);
        assertEquals("1", outStream.toString());
    }
}
