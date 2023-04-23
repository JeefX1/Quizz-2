import java.io.*;
import java.util.Random;


class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}


class ArrayUtils {

    private ArrayUtils() {}


    public static int[] readIntegersFromFile(String filename) throws IOException, NumberFormatException, InvalidNumberException {
        int[] array = new int[10];
        Random random = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < array.length; i++){
            int rand_num = random.nextInt(21)-10;
            writer.write(rand_num + "\n");
        }
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int index = 0;
        while ((line = reader.readLine()) != null && index < 10) {
            try {
                int number = Integer.parseInt(line);
                if (number < 0) {
                    throw new InvalidNumberException("Invalid number: " + number);
                }
                array[index++] = number;
            } catch (NumberFormatException e) {
                System.out.println("Error reading line: " + line);
            }
        }
        reader.close();
        return array;
    }


    public static int sum(int[] array) throws ArithmeticException {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            try {
                sum = Math.addExact(sum, array[i]);
            } catch (ArithmeticException e) {
                System.out.println("Error adding numbers: " + e.getMessage());
            }
        }
        return sum;
    }
}


public class Main {
    public static void main(String[] args) {
        try {
            int[] array = ArrayUtils.readIntegersFromFile("numbers.txt");
            int sum = ArrayUtils.sum(array);
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        } catch (InvalidNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
