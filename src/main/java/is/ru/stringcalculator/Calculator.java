package is.ru.stringcalculator;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Calculator {
	
	private static String delimiter;
	
	public static int add(String text){
		if(text.equals("")){
			return 0;
		}

	       	else if(text.startsWith("//[")){
                        delimiter = Pattern.quote(text.substring(3, text.indexOf("]")));
                        return sum(splitCustomDelim(onlyNumbersToAdd(text)));
                }

		else if(text.startsWith("//")){
			delimiter = String.valueOf(text.charAt(2));
			return sum(splitCustomDelim(onlyNumbersToAdd(text)));
		}

		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}

		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}
      
	private static String[] splitNumbers(String numbers){
		return numbers.split(",|\\n");
	}

	private static String[] splitCustomDelim(String numbers){
		return numbers.split(delimiter);
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
