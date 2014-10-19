package is.ru.stringcalculator;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Calculator {
	
	public static int add(String text){
		
		ArrayList<String> delimiter = new ArrayList<String>();

		if(text.equals("")){
			return 0;
		}

	       	else if(text.startsWith("//[")){
			delimiter = addDelimiters(text);		
                        return sum(splitCustomDelim(onlyNumbersToAdd(text), delimiter));
                }

		else if(text.startsWith("//")){
			delimiter.add(String.valueOf(text.charAt(2)));
			return sum(splitCustomDelim(onlyNumbersToAdd(text), delimiter));
		}

		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}

		else
			return 1;
	}

	private static ArrayList<String> addDelimiters(String text) {
		ArrayList<String> newDelimiters = new ArrayList<String>();
		int currPosStart = text.indexOf("[");
		int prevPosStart = 0;
		int currPosEnd = text.indexOf("]");
		int prevPosEnd = 0;
		// Found this clever way on stack overflow, created by Andreas Wederbrand
		int countDelimiters = text.length() - text.replace("[", "").length();
		boolean end = false;
		for (int i = 0; i < countDelimiters; i++) {
			newDelimiters.add(text.substring(currPosStart + 1, currPosEnd));
			prevPosStart = currPosStart;
			currPosStart = text.indexOf("[", prevPosStart + 1);
			prevPosEnd = currPosEnd;
                        currPosEnd = text.indexOf("]", prevPosEnd + 1);
		}
		return newDelimiters;
		
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}
      
	private static String[] splitNumbers(String numbers){
		return numbers.split(",|\\n");
	}

	private static String[] splitCustomDelim(String numbers, ArrayList<String> delimiter) {
		for(int i = 0; i < delimiter.size(); i++) {
			numbers = onlyNumbersToAdd(numbers).replace(delimiter.get(i), ",");		
		}
		return numbers.split(",");
	}

	private static void checkIfNegative(String[] numbers){
		String negNumbers = new String();
		negNumbers = "Negatives not allowed: ";
		boolean count = false;
		for(String number : numbers){
			if(toInt(number) < 0){
				negNumbers += number;
				negNumbers += ",";
				count = true;
			}
		}
		if(count){
			throw new RuntimeException(negNumbers.substring(0,negNumbers.length()-1));
		}
	}

	private static String onlyNumbersToAdd(String text){
		String newString = new String();
		newString = text.substring(text.indexOf("\n") + 1, text.length());
		return newString;
	}

	private static int sum(String[] numbers){
 		int total = 0;
		checkIfNegative(numbers);
        	for(String number : numbers){ 
			if(toInt(number) <= 1000) 
				total += toInt(number);
		}
		return total;
    }

}
