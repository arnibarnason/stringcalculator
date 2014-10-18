package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
	public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
	}

	@Test
	public void testNewLineAndCommaDelimmiter(){
    	assertEquals(6, Calculator.add("1\n2,3"));
   	}

	@Test
	public void testCustomDelimiter(){
    	assertEquals(3, Calculator.add("//;\n1;2"));
   	}

	@Test(expected = RuntimeException.class)
	public void testNegativeNumberException()
        		throws Exception {
    	assertEquals("Negatives not allowed: -4,-1", Calculator.add("2,-4,7,-1"));
	}

	@Test(expected = RuntimeException.class)
	public void testNegativeNumberExceptionNewInput()
        		throws Exception {
    	assertEquals("Negatives not allowed: -2,-4,-7", Calculator.add("//;\n-2;-4;-7;1"));
	}
}
