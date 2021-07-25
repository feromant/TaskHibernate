package ru.maznichenko.jmtasks;

import java.util.Scanner;

import static ru.maznichenko.jmtasks.NumberType.ARABIC;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String expression = scan.nextLine();
        scan.close();

        Parser parser = new Parser();
        InputData data = parser.parseInput(expression);

        int a, b;
        if (data.getType() == ARABIC) {
            a = Integer.parseInt(data.getOperands()[0]);
            b = Integer.parseInt(data.getOperands()[1]);
        } else {
            a = NumbersConverter.convertRomanToArabic(data.getOperands()[0]);
            b = NumbersConverter.convertRomanToArabic(data.getOperands()[1]);
        }
        if (a < 1 || a > 10 || b < 1 || b > 10)
            throw new IllegalArgumentException("Ошибка: операнды должны находиться в диапазоне [1, 10]");
        int result;
        switch (data.getOp()) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> result = a / b;
            default -> throw new IllegalStateException("Ошибка: недопустимый знак операции");
        }

        System.out.println("Output:");
        if (data.getType() == ARABIC)
            System.out.println(result);
        else {
            String resultRoman = NumbersConverter.convertArabicToRoman(result);
            System.out.println(resultRoman);
        }
    }
}
