package ru.maznichenko.jmtasks;

import java.util.Scanner;

import static ru.maznichenko.jmtasks.NumberType.ARABIC;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String expression = scan.nextLine();
        scan.close();

        InputData data = Parser.parseInput(expression);
        int result = Calculator.calculate (data);

        System.out.println("Output:");
        if (data.getType() == ARABIC)
            System.out.println(result);
        else {
            String resultRoman = NumbersConverter.convertArabicToRoman(result);
            System.out.println(resultRoman);
        }
    }
}
