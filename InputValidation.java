package lotteryGame;

public class InputValidation {

	    //enable line wrap in your IDE
	    public static String[] testInputs = {"yes", "no", "y", "n", "YES", "NO", "123", "85.0", "-1", "0", "d8($()*", "1988", "32", "50.0", "50.00", "50.01", "50.1", "09", "12", "200", "100", "35.33", "", "\n", "\t"}; 
	    
	    //pet name must be at least 3 characters because generateLottoNumbers
	    //takes the ASCII value of the 3rd character.
	    public static boolean validPetName(String name){
	        return name.length() > 2;
	    }
	    
	    //makes sure the actor's name is not empty
	    //doesn't check for anything else. Heard someone tried
	    // to name their baby the @ symbol
	    public static boolean validActorName(String name){
	        return name.length() > 0; 
	    }
	    
	    //true for all variations of y yes n and no
	    public static boolean isYesNo (String response){
	        String[] answers = {"y", "yes", "n", "no"};
	        for(String answer : answers){
	            if(response.equalsIgnoreCase(answer)){
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public static boolean isInt(String str){
	        try {
	            int n = Integer.parseInt(str);
	        } catch(NumberFormatException nfe){
	            return false;
	        }
	        return true;
	    }
	    
	    //tests to see if input is two digits. used for checking car model number
	    public static boolean isTwoChars(String str){
	        return str.length()==2;
	    }
	    
	    public static boolean isQBJersey(String str){
	        if(isInt(str)){
	            int n = Integer.parseInt(str);
	            //1 to 19 are current QB jersey positions. 0 has ben used historically https://en.wikipedia.org/wiki/National_Football_League_uniform_numbers#Current_system
	            return isInt(str) && n >=0 && n<=19; 
	        }
	        return false;
	    }
	    
	    public static boolean isCarModel(String str){
	        //short circuit evaluation means parseInt won't trigger an error if it's not parseable.
	        return isInt(str) && isTwoChars(str) && Integer.parseInt(str) > -1;
	    }
	    
	    //if the number is between 1 and 50 inclusive return true
	    public static boolean between1And50(String str){
	        //uses double not int because otherwise 3.5 would return false. 
	        //but in implementation of other methods, all numbers are treated as ints. 
	        try{
	            double d = Double.parseDouble(str);
	            return d >= 1.0 && d <= 50.0;
	        }
	        catch(NumberFormatException nfe){
	            return false;
	        }
	    }
	   
	    //this method calls all the above boolean methods on the testInputs string array. 
	   public static void runTests(){
	    for(String input : testInputs){
	        System.out.println(input+ "\tisYesNo?\t" + isYesNo(input) +"\tisInt?\t" + isInt(input) +"\tisTwoChars\t" + isTwoChars(input) + "\tisQBJersey\t" + isQBJersey(input) + "\tisCarModel\t" + isCarModel(input) +"\tb/t1.50\t" + between1And50(input));
	        }
	   }
	}
