package main.java.com.hillel.polezhaiev.homework10;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class ValueCalculator {

    private int numbersLength;
    private int halfNumbersLength;
    private double [] numbers;

    public ValueCalculator(int numbersLength) {
        this.numbersLength = numbersLength;
        halfNumbersLength = numbersLength / 2;
        numbers = new double[numbersLength];
    }

    public boolean doubleArrays(){

        if(numbers.length == 0){
            return false;
        }

        int lengthOfDoubleArr = numbers.length / 2;
        Instant start = Instant.now();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 1;
        }

        double [] first = Arrays.copyOfRange(numbers, 0, lengthOfDoubleArr);
        double [] second = Arrays.copyOfRange(numbers, lengthOfDoubleArr, numbers.length);

        System.out.print("First arr: ");
        for(double i: first){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.print("Second arr: ");
        for(double i: second){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        new Thread(() ->{
            for (int i = 0; i < first.length; i++) {
                first[i] = (float)(first[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < second.length; i++) {
                second[i] = (float)(second[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }).start();

        numbers = DoubleStream.concat(DoubleStream.of(first), DoubleStream.of(second)).toArray();


        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Numbers" + "[" + i + "]" + " = " + numbers[i] + ";");
        }

        System.out.println();
        Instant end = Instant.now();
        Duration different = Duration.between(start, end);

        System.out.println("Time of processing = " + different.toMillis() + " milliseconds");
        return true;
    }

    public void setNumbersLength(int numbersLength) {
        this.numbersLength = numbersLength;
    }

    public int getNumbersLength() {
        return numbersLength;
    }
}
