import java.util.List;

/**
 * @Author: Nathan Dobbins
 */
public class CalcHistory {
    private double result;
    private List<Integer> numbers;
    private String operation;

    /**
     * Constructor that sets the results, list of numbers, and operation performed
     * @param result
     * @param numbers
     * @param operation
     */
    public CalcHistory(double result, List<Integer> numbers, String operation){
        this.result = result;
        this.numbers = numbers;
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String toString(){
        String string = "" + numbers.get(0);
        for(int i = 1; i < numbers.size(); i++){
            string += " " + operation + " " + numbers.get(i);
        }
        string += " = " + result;
        return string;
    }
}
