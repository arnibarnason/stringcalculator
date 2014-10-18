package is.ru.stringcalculator;

public class Calculator {
	
	private static String delimmiter;

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		
		else if(text.startsWith("//")){
			delimmiter = String.valueOf(text.charAt(2));;
			return sum(splitCustomDelim(text));
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
		numbers = numbers.substring(4, numbers.length());
		return numbers.split(delimmiter);
	}

	private static int sum(String[] numbers){
 		int total = 0;
        	for(String number : numbers){
			total += toInt(number);
		}
		return total;
    }



}
