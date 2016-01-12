import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

/**
 * Created by dobbinsn on 12/16/2015.
 */
public class Calculator {

    public static ArrayList<CalcHistory> history = new ArrayList<CalcHistory>();

    /**
     * Starts the calculator program, and gets command input
     * @param args
     * Author: Noah Brusky
     */
    public static void main(String[] args) {
        printBanner();
        Scanner in = new Scanner(System.in);
        String input = "";
        while(!input.equals("exit")){
            input = in.nextLine();
            parseInput(input);
        }
    }

    /**
     * Adds the list of integer numbers up together and returns the sum.
     * @param nums
     * @return
     * Author: Noah Brusky
     */
    public static int add(List<Integer> nums){

        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        addToHistory((double)sum, nums, "+");
        return sum;
    }

    /**
     * Takes the first number from a given list of integers and subtracts the remaining integers in the list
     * from the first number, and returns the difference.
     * @param nums
     * @return
     * Author: Noah Brusky
     */
    public static int subtract(List<Integer> nums){

        int difference = nums.get(0);
        for(int i=1; i<nums.size(); i++){
            difference -= nums.get(i);
        }

        addToHistory((double)difference, nums, "-");
        return difference;
    }

    /**
     * Multiplies each number from a list of integers together and returns the product.
     * @param nums
     * @return
     * Author: Noah Brusky
     */
    public static int multiply(List<Integer> nums){

        int product = 0;
        if(!nums.isEmpty()){
            product = 1;
        }
        for(int num : nums){
            product = product * num;
        }

        addToHistory((double)product, nums, "*");
        return product;
    }

    /**
     * Divides the first number in the list by those following it
     * @param nums
     * @return
     * Author: Noah Brusky
     */
    public static double divide(List<Integer> nums){

        double quotient = (double)nums.get(0);
        for(int i=1; i<nums.size(); i++){
            quotient = quotient/(double)nums.get(i);
        }

        addToHistory(quotient, nums, "/");
        return quotient;
    }

    /**
     * Squares the first number in the list
     * @param nums
     * @return
     * Author: Noah Brusky
     */
    public static int square(List<Integer> nums){

        int squared = 0;
        try{
            squared = nums.get(0)*nums.get(0);
        } catch (IndexOutOfBoundsException e){
            System.out.println("No integer found.");
        }

        addToHistory((double)squared, nums, "^");
        return squared;
    }

    /**
     * parses the input, calls the correct command, and prints the results
     * @param input
     * Author: Noah Brusky and Nate Dobbins
     */
    public static void parseInput(String input){
        String[] args = input.split(" ");
        String command = args[0];
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for(int i=1; i<args.length; i++){
            if(args[i].charAt(0) == '!') {
                int historyNum;
                try {
                    historyNum = Integer.parseInt(args[i].substring(1));
                    numbers.add((int)getHistory(historyNum));
                } catch (NumberFormatException e) {
                    System.out.println("'" + args[i] + "' is not a valid input. Inputs must all be integers.");
                } catch (IndexOutOfBoundsException e){
                    System.out.println("'" + args[i].substring(1) + "' is not a valid history number");
                }
            }else{
                try {
                    numbers.add(Integer.parseInt(args[i]));
                } catch (Exception e) {
                    System.out.println("'" + args[i] + "' is not a valid input. Inputs must all be integers.");
                }
            }
        }
        if(command.equals("add")) {
            System.out.println(add(numbers));
        } else if(command.equals("sub")) {
            System.out.println(subtract(numbers));
        } else if(command.equals("mul")) {
            System.out.println(multiply(numbers));
        } else if(command.equals("div")) {
            System.out.println(divide(numbers));
        } else if(command.equals("history")) {
            printHistory();
        } else if(command.equals("clear")) {
            clearHistory();
            System.out.println("History Cleared");
        } else if(command.equals("sq")) {
            System.out.println(square(numbers));
        } else if(command.equals("exit")) {
            System.out.println("Exiting.");
        } else {
            System.out.println("Invalid command.");
        }
    }

    /**
     * Adds a calculation to the history
     * @param result
     * @param nums
     * @param operation
     * Author: Nate Dobbins
     */
    public static void addToHistory(double result, List<Integer> nums, String operation){
        history.add(new CalcHistory(result,nums,operation));

        if(history.size() > 5){
            history.remove(0);
        }
    }

    /**
     * prints the history
     * Author:Nathan Dobbins
     */
    public static void printHistory(){
        System.out.println("Calculation History: ");
        for(int i = 0; i < history.size(); i++){
            System.out.println("\t" + i + ". " + history.get(i).toString());
        }
    }

    /**
     * Gets an item out of history, and throws an error if the desired history item
     * doesnt exist
     * @param resultNum
     * @return
     * @throws IndexOutOfBoundsException
     * Author: Nathan Dobbins
     */
    public static double getHistory(int resultNum) throws IndexOutOfBoundsException{
        return history.get(resultNum).getResult();
    }

    /**
     * Clears the history
     * Author: Nathan Dobbins
     */
    public static void clearHistory(){
        history.clear();
    }

    public static void printBanner(){
        System.out.println("Welcome to the Calculator program!");
        System.out.println("Please type a command:\n");
    }
}
