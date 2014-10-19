package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

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
	
	@Rule
 	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwExceptionIfNegatives() {
    		thrown.expect( RuntimeException.class );
    		thrown.expectMessage("Negatives not allowed: -4,-1");

    		Calculator.add("2,-4,7,-1");
	}

        @Test
        public void throwExceptionIfNegativesCustomDelimiter() {
                thrown.expect( RuntimeException.class );
                thrown.expectMessage("Negatives not allowed: -4,-1,-2");

                Calculator.add("//;\n2;-4;-1;-2");
        }

	@Test
	public void dontIgnoreThousand() {
		assertEquals(1019, Calculator.add("//&\n15&4&1000"));
	}

        @Test
        public void ignoreBiggerThanThousand() {
                assertEquals(19, Calculator.add("//&\n15&4&1001"));
        }

}
