package lotteryGame;

import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class AsciiChars {

	public static void printNumbers() 
	{
		for (int i = 48; i < 58; i++) {
			String n = Character.toString((char) i);
			System.out.println(n);
			}
		}
	public static void printLowerCase() 
	{
			for (int i = 97; i < 123; i++) {
				String n = Character.toString((char) i);
				System.out.println(n);
			}
		}
	public static void printUpperCase() 
	{
				for (int i = 65; i < 91; i++) {
					String n = Character.toString((char) i);
					System.out.println(n);
			}	
	};
	
	
	 public static int[] generateLottoNumbers(String redCar, String petName, int petAge, int luckyNumber, String doYouHaveAFavoriteQB, int qbJerseyNumber, int carYearModel, String favoriteActorFirstName, int randomNumberOnetoFifty){
//       
       int[] lottoNumbers = {0,1,2,3,4,5}; //place holder values
       
       //generate three random integers from 1 to 75
       int firstRandom = (int) (Math.random() * 75) + 1; //for magic ball
       int secondRandom = (int) (Math.random() * 75) + 1; 
       int thirdRandom = (int) (Math.random() * 75) + 1;  
    
//     generate magic ball first
     //multiply luckyNumber by a random int (firstRandom) then subtract 75 if it is greater than 75
     int magicBall = (int) (luckyNumber * firstRandom);
     while(magicBall > 75){
         magicBall-=75;
     }
     lottoNumbers[5]=magicBall; //magicBall must be the last item in the array
     //two digit year model + lucky number
     lottoNumbers[0] = carYearModel + luckyNumber;
     //first letter of favorite actor/actress to an integer by ASCII
     lottoNumbers[1] = (int) favoriteActorFirstName.charAt(0); 
     //hard code as 42
	lottoNumbers[2] = 42; //best number
     //age of favorite pet + car model year
	lottoNumbers[3] = petAge + carYearModel;
     //3rd letter of favorite pet as ASCII int
	lottoNumbers[4] = (int) petName.charAt(2);
     
     //if they are outside of the boundaries (1 to 65) fix it. 
     //-1 to not adjust magicBall
     for (int i=0; i < lottoNumbers.length-1; i++) {
         lottoNumbers[i] = stayBounded(lottoNumbers[i], 1, 65);
     }
     
     return lottoNumbers;
     
 }
 //boundaries for the lotto numbers are 1 to 65 inclusive
 //add or subtract by max if they are outside of the boundary
 public static int stayBounded(int value, int min, int max){
     while(value < min){
         value+=max;
     }
     while(value > max){
         value-=max;
     }
     return value;
 }
 public static void printLottoNumbers(int[] lottoNumbers){
     //desired format
    // Lottery numbers: 4, 17, 15, 52, 26  Magic ball: 22
     System.out.print("Lottery numbers: "); 
     //stop 1 less because magic ball needs to be printed special
     for(int i=0; i < lottoNumbers.length-1; i++){
         System.out.print(Integer.toString(lottoNumbers[i]) +", ");
     }
     //print magic ball
     System.out.print("Magic ball: " +lottoNumbers[lottoNumbers.length-1] +"\n");        
 }
 
 //input validation methods to follow
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
 /**
  * @param args the command line arguments
  */
 public static void main(String[] args) {
     
     //fields for calculating the lotto number
     //default values are for testing so I don't have to enter inputs over and over
     String redCar="no"; //not used in generating lotto numbers
     String petName="Skoogie";
     int petAge=6;
     int luckyNumber=7;
     String doYouHaveAFavoriteQB="no";
     int qbJerseyNumber=-1;
     int carYearModel=8; //two digits
     String favoriteActorFirstName="Johnny";
     int randomNumberOnetoFifty=4; //"random"
     String line=""; //for reading the scanner input. If the input is valid then an above field gains the value of line
     
     
     //prints ASCII characters
     AsciiChars.printNumbers();
     AsciiChars.printLowerCase();
     AsciiChars.printUpperCase();
     //gets name and prints it
     Scanner scanner = new Scanner(System.in);
     System.out.println("Please enter your name:");
     String name = scanner.nextLine();
     System.out.printf("Hi %s\n", name);
     
     //go into picking lotto numbers if yes.
     System.out.println("Do you wish to continue and pick your lotto numbers? (say yes)");
     String choice = scanner.nextLine();
     while(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
         System.out.println("Do you have a red car? (yes, no)");
         line = scanner.nextLine();
         while(!InputValidation.isYesNo(line)){
             System.out.println("Invalid choice, try again");
             line=scanner.nextLine();
         }
         redCar = line;
         System.out.println("What is the name of your favorite pet?");
         line = scanner.nextLine();
         while(!InputValidation.validPetName(line)){
             System.out.println("Petname must be at least 3 characters");
             line = scanner.nextLine();
         }
         petName =  line;
         System.out.println("What is the age of your favorite pet?");
         line = scanner.nextLine();
     while(!InputValidation.isInt(line) || Integer.parseInt(line) < 0){
             
             System.out.println("Pet age must be an an int 0 or higher. Try again");
             line = scanner.nextLine();
         }
         petAge = Integer.parseInt(line);
        
         System.out.println("What is your lucky number (ints only)");
         line = scanner.nextLine();
         while(!InputValidation.isInt(line)){
             System.out.println("Not an int. Try again");
             line = scanner.nextLine();
         }
         luckyNumber = Integer.parseInt(line);
         
         System.out.println("Do you have a favorite quarterback? (yes, no)");
         line = scanner.nextLine();
         while(!InputValidation.isYesNo(line)){
             System.out.println("Choose yes or no");
             line = scanner.nextLine();
         }
         doYouHaveAFavoriteQB = line; //if not yes or y then dont' ask for jersey number;
         if(doYouHaveAFavoriteQB.equalsIgnoreCase("yes") || doYouHaveAFavoriteQB.equalsIgnoreCase("y")){
             System.out.println("What is your favorite QB's jersey number");
             line = scanner.nextLine();
             while(!InputValidation.isQBJersey(line)){ //isQBJersey checks if it is int AND if it is 0 to 19 inclusive. 1 to 19 is current and 0 is a historical value
                 System.out.println("QB Jerseys must be an int from 0 to 19."
                         + "\nhttps://en.wikipedia.org/wiki/National_Football_League_uniform_numbers#Current_system");
                 line = scanner.nextLine();
             }
             qbJerseyNumber = Integer.parseInt(line);
         } else{
             qbJerseyNumber = -1; //means there is no favorite QB
         }
         System.out.println("What is the two-digit model year of your car?");
         line = scanner.nextLine();
         while(!InputValidation.isCarModel(line)){
             System.out.println("Car model must be two digits");
             line = scanner.nextLine();
         }
         carYearModel = Integer.parseInt(line);
         System.out.println("What is the first name of your favorite actor or actresss");
         line = scanner.nextLine();
         while(!InputValidation.validActorName(line)){
             System.out.println("Name cannot be empty");
             line = scanner.nextLine();
         }
         favoriteActorFirstName = line;
         System.out.println("Enter a random number between 1 and 50");
         line = scanner.nextLine();
         while(!InputValidation.between1And50(line)){
             System.out.println("Number must be between 1 and 50 inclusive.");
             line = scanner.nextLine();
         }
         //accepts doubles and casts to int for generateLottoNumbers
         randomNumberOnetoFifty = (int) Double.parseDouble(line);

         
         int[] lottoNumbers = generateLottoNumbers(redCar, petName, petAge, luckyNumber, doYouHaveAFavoriteQB, qbJerseyNumber, carYearModel, favoriteActorFirstName, randomNumberOnetoFifty); 
 
         //sort lotto numbers, but NOT magicBall
         Arrays.sort(lottoNumbers, 0, lottoNumbers.length-1);
         printLottoNumbers(lottoNumbers);
         
         //repeat if choice is y or yes
        System.out.println("Play again? (yes, no)");
        choice = scanner.nextLine();
     }
     System.out.println("Thank you for playing.");
 }
}
	