import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests the Calculator
 */
public class CalculatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {

    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Tests the add method
     * Nate Dobbins
     */
    @Test
    public void testAdd(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        assertEquals(13,Calculator.add(Arrays.asList(nums)),0);
        Calculator.parseInput("add 1 10 3 -1");
        assertThat(outContent.toString(), CoreMatchers.containsString("13"));
    }

    /**
     * Tests Subtract
     * Nate Dobbins
     */
    @Test
    public void testSubtract(){
        Integer[] nums = new Integer[] {20,10,3,-1};
        assertEquals(8,Calculator.subtract(Arrays.asList(nums)),0);
        Calculator.parseInput("sub 20 10 3 -1");
        assertThat(outContent.toString(), CoreMatchers.containsString("8"));
    }

    /**
     * Tests Multiply
     * Nate Dobbins
     */
    @Test
    public void testMultiply(){
        Integer[] nums = new Integer[] {2,4,3,-1};
        assertEquals(-24,Calculator.multiply(Arrays.asList(nums)),0);
        Calculator.parseInput("mul 2 4 3 -1");
        assertThat(outContent.toString(), CoreMatchers.containsString("-24"));
    }

    /**
     * Tests divide
     * Nate Dobbins
     */
    @Test
    public void testDivide(){
        Integer[] nums = new Integer[] {16,2,4};
        assertEquals(2,Calculator.divide(Arrays.asList(nums)),0);
        Calculator.parseInput("div 16 2 4");
        assertThat(outContent.toString(), CoreMatchers.containsString("2.0"));
    }

    /**
     * Test divide
     * Noah Brusky
     */
    @Test
    public void testSquare(){
        assertEquals(49, Calculator.square(Arrays.asList(new Integer[] {7})), 0);
        Calculator.parseInput("sq 7");
        assertThat(outContent.toString(), CoreMatchers.containsString("49"));

        Calculator.square(Arrays.asList(new Integer[] {}));
        assertThat(outContent.toString(), CoreMatchers.containsString("No integer found."));
    }

    /**
     * Tests the history size
     * Noah Brusky
     */
    @Test
    public void testHistorySize(){
        Calculator.clearHistory();
        Calculator.parseInput("clear");
        assertThat(outContent.toString(), CoreMatchers.containsString("History Cleared"));
        assertEquals(0,Calculator.history.size(),0);
        Calculator.add(Arrays.asList(new Integer[] {1,2,3,4}));
        assertEquals(1,Calculator.history.size(),0);
        Calculator.clearHistory();
        assertEquals(0,Calculator.history.size(),0);
    }

    /**
     * Tests print history
     * Noah Brusky
     */
    @Test
    public void testPrintHistory(){
        Calculator.clearHistory();
        Integer[] nums1 = new Integer[] {1,10,3,-1};
        Integer[] nums2 = new Integer[] {20,10,3,-1};
        Calculator.add(Arrays.asList(nums1));
        Calculator.subtract(Arrays.asList(nums2));
        Calculator.parseInput("history");
        assertThat(outContent.toString(), CoreMatchers.containsString("1 + 10 + 3 + -1 = 13"));
        assertThat(outContent.toString(), CoreMatchers.containsString("20 - 10 - 3 - -1 = 8"));
    }

    /**
     * Tests Exit
     * Noah Brusky
     */
    @Test
    public void testExit(){
        Calculator.parseInput("exit");
        assertThat(outContent.toString(), CoreMatchers.containsString("Exiting."));
    }

    /**
     * Tests invalid command
     * Noah Brusky
     */
    @Test
    public void testInvalidCommand(){
        Calculator.parseInput("exi");
        assertThat(outContent.toString(), CoreMatchers.containsString("Invalid command."));
    }

    /**
     * Tests invalidNumber entry
     * Noah Brusky
     */
    @Test
    public void testInvalidNumberEntry(){
        Calculator.parseInput("add ten 4");
        assertThat(outContent.toString(), CoreMatchers.containsString("not a valid input"));
    }

    /**
     * Tests history number replacement
     * Noah Brusky
     */
    @Test
    public void testNumberReplacement(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        Calculator.add(Arrays.asList(nums));
        Calculator.parseInput("add !0 1");
        assertThat(outContent.toString(), CoreMatchers.containsString("14"));

        Calculator.parseInput("add !7 1");
        assertThat(outContent.toString(), CoreMatchers.containsString("not a valid history number"));

        Calculator.parseInput("add !ten 3");
        assertThat(outContent.toString(), CoreMatchers.containsString("not a valid input"));

    }

    /**
     * Tests initial print message
     * Noah Brusky
     */
    @Test
    public void testPrintBanner(){
        Calculator.printBanner();
        assertThat(outContent.toString(), CoreMatchers.containsString("Welcome"));
    }

    /**
     * Tests calcHistory result set
     * Noah Brusky
     */
    @Test
    public void testSetResult(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        CalcHistory t = new CalcHistory(13.0, Arrays.asList(nums), "+");
        t.setResult(15.0);
        assertEquals(15.0, t.getResult(), 0);
    }

    /**
     * Tests calcHistory get numbers
     * Noah Brusky
     */
    @Test
    public void testGetNumbers(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        CalcHistory t = new CalcHistory(13.0, Arrays.asList(nums), "+");
        assertEquals(nums[0], t.getNumbers().get(0), 0);
        assertEquals(nums[1], t.getNumbers().get(1), 0);
        assertEquals(nums[2], t.getNumbers().get(2), 0);
        assertEquals(nums[3], t.getNumbers().get(3), 0);
    }

    /**
     * Tests calcHistory set numbers
     * Noah Brusky
     */
    @Test
    public void testSetNumbers(){
        Integer[] nums1 = new Integer[] {1,10,3,-1};
        Integer[] nums2 = new Integer[] {5,3,1,7};
        CalcHistory t = new CalcHistory(13.0, Arrays.asList(nums1), "+");
        t.setNumbers(Arrays.asList(nums2));
        assertEquals(nums2[0], t.getNumbers().get(0), 0);
        assertEquals(nums2[1], t.getNumbers().get(1), 0);
        assertEquals(nums2[2], t.getNumbers().get(2), 0);
        assertEquals(nums2[3], t.getNumbers().get(3), 0);
    }

    /**
     * Tests calcHistory get operation
     * Noah Brusky
     */
    @Test
    public void testGetOperation(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        CalcHistory t = new CalcHistory(13.0, Arrays.asList(nums), "+");
        assertTrue(t.getOperation().equals("+"));
    }

    /**
     * Tests calcHistory set operation
     * Noah Brusky
     */
    @Test
    public void testSetOperation(){
        Integer[] nums = new Integer[] {1,10,3,-1};
        CalcHistory t = new CalcHistory(13.0, Arrays.asList(nums), "+");
        t.setOperation("-");
        assertTrue(t.getOperation().equals("-"));
    }
}