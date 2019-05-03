import java.util.*;

public class Assn5{ 
	

    public static ArrayList<String> arrList = new ArrayList<String>(); //an array consisting of all the combinations of operators used with the length of the input number 
    public static ArrayList<String> evalExpr = new ArrayList<String>(); //the resulting array once the numbers have been placed inline with the operators

    public static void main(String[] args){
        char[] opSet = {'+', '-', '@'}; //character array to be passed into the generator
        String nums = args[0];// user input String number in terminal
        int sum = Integer.parseInt(args[1]);// user input String sum in terminal
        int count = 0;// keeps track of how many solutions were found
        int k = nums.length(); //storing the length of the input string of numbers, ex. 123456789 = 9
        
        generatePermutations(opSet, "", opSet.length, k); //creates every possible equation for the number and outputs it in an array
        insertNumbers(arrList, nums); // takes the resulting array of operators from the previous line and inserts numbers inbetween the operators

        // loops through the list of evaluable expressions ex. (1+2+3+4-5+67+89) and outputs if the expression equals the target number
        for(int i = 0; i < evalExpr.size(); i++){
            int total = evaluate(evalExpr.get(i));// gets the expression total
            if(total == sum){// checks if expression total is equal to the target number passed in
                if (evalExpr.get(i).charAt(1) == '+')
                    System.out.println(evalExpr.get(i).substring(2) + " = " + sum);
                else
                    System.out.println(evalExpr.get(i).substring(1) + " = " + sum);// output expresstion and total
                count++;// increments the number of found solutions
            }
        }
        System.out.println("There are " + count + " possible solutions.");// output number of solutions 
    } 
    

	//method used to fill an array list with a list of every combination of operators that are passed in as
	/*
	 * set: a set of character operators such as +, -, @
	 * prefix: a reacurring string that is built up from the elements of the set
	 * n: the amount of elements within the character array
	 * k: the amount of characters within the target number ex. 123456789 = 9
	 */
    public static void generatePermutations(char[] set, String prefix, int n, int k){ 
        if (k == 0){ //checking to see if the very first run through
            // @ represents 2 or more numbers that are together
            if(prefix.charAt(0) != '@'){ //adding the very first character of the expression as long as it is not an @ sign giving us equations with only + or - in the front
                arrList.add(prefix);
            }
            return; 
        } 
		//iterates through each character in the operator and recursively builds the string of operators resulting in something that looks like: ++-+-+@+
        for (int i = 0; i < n; ++i){ 
            String newPrefix = prefix + set[i];  
            generatePermutations(set, newPrefix, n, k - 1);  
        } 
    } 
    
	//takes the resulting array from generatePermutations in the form (+-+-++@+) and inserts number to create a list of evaluatable strings such as (1+2-3+4-5+6+78+9)
    public static void insertNumbers(ArrayList<String> arr, String digits){
        for(int i = 0; i < arr.size(); i++){ 	//looping through each string in the array to modify
            String str = ""; //temp string that will be used to build the evaluatable expression
            for(int j = 0; j < digits.length(); j++){ //now looping through one string within the list and beggining to insert numbers in it
                if(j == 0){ //to make parsing the expression easier a 0 is added in front of each one
                    str = str + "0"; 
                }
                str = str + (arr.get(i)).charAt(j); //because the expression always follows the pattern (num)(op)(num)(op)...(num)
                str = str + (digits).charAt(j); 	//the string is built following the added zero, by grabbing an op then a number and so on until the whole expression is built 
            }
            str = str.replace("@", ""); //in order to create the scenarios that look like this: (1+2-3+4-5+6+78+9) an @ sign was used to combine digits together ex. (1+2-3+4-5+6+7@8+9), thus it is removed at the end
            evalExpr.add(str); //adding the created expression to the list of evaluatable expressions
        }
    }
    
    public static int evaluate(String s){
        int i;// used to loop through each char in String
        int total = 0;// keeps track of expression total

        // gets integer from expression
        if(!s.contains("+") && !s.contains("-")){
            return Integer.parseInt(s);
        }

        // gets operator from expression
        for(i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                break;
            }
        }
    
        String x = s.substring(0, i);// first integer
        String y = s.substring(i + 1, s.length());// second integer
    
        // updates total based on the operator
        switch(s.charAt(i)){
            case '+':// addition
                total = evaluate(x) + evaluate(y);
                break;
            case '-':// subtraction
                total = evaluate(x) - evaluate(y);
                break;
        }
        return total;// returns expression total
    }    

}