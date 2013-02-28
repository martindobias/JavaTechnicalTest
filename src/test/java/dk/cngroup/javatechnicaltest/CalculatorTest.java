package dk.cngroup.javatechnicaltest;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;

public class CalculatorTest {

    @Test
    public void testCalculator1() throws Exception {
        Calculator calculator = new Calculator(new BufferedReader(new StringReader("add 3\n" +
                "multiply 5\n" +
                "\n" +
                "  apply 2\n" +
                "add     10\n" +
                "apply 3\n" +
                "add 8  \n")));

        final HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();

        calculator.evaluate(true, new Report() {
            @Override
            public void report(int position, int result) {
                results.put(position, result);
            }
        });

        Assert.assertEquals("Number of results", 2, results.size());
        Assert.assertEquals("First result", 25, (int) results.get(3));
        Assert.assertEquals("Second result", 40, (int) results.get(5));
    }

    @Test
    public void testCalculator2() throws Exception {
        try {
            Calculator calculator = new Calculator(new BufferedReader(new StringReader("aDd 2\n" +
                    "\n" +
                    "POKE 2\n")));

            calculator.evaluate(true, null);
        } catch (Exception e) {
            return; // expected exception
        }
        Assert.fail("Should have produced an exception");
    }
}
