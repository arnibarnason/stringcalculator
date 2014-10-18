package is.ru.stringcalculator;
import java.util.ArrayList;

public class Calculator {
	
	private static String delimmiter;
	
	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		
		else if(text.startsWith("//")){
			delimmiter = String.valueOf(text.charAt(2));
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
		return numbers.split(delimmiter);
	}

	private static void checkIfNegative(String[] numbers){
		ArrayList<String> negatives = new ArrayList<String>();
		for(String number : numbers){
			if(toInt(number) < 0){
				negatives.add(number);
			}
		}
		if(negatives.size() != 0){
			throw new RuntimeException("Negatives not allowed: -4,-1");
		}
	}

	private static String onlyNumbersToAdd(String text){
		String newString = new String();
		newString = text.substring(4, text.length());
		return newString;
	}

	private static int sum(String[] numbers){
 		int total = 0;
		checkIfNegative(numbers);
        	for(String number : numbers){ 
			total += toInt(number);
		}
		return total;
    }



}
