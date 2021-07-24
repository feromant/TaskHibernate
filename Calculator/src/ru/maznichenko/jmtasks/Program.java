package ru.maznichenko.jmtasks;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String expression = scan.nextLine();
        scan.close();

        Parser parser = new Parser();
        parser.parseInput(expression);

        int a, b;
        if (parser.arabic) {
            a = Integer.parseInt(parser.operands[0]);
            b = Integer.parseInt(parser.operands[1]);
        } else {
            a = NumbersConverter.convertRomanToArabic(parser.operands[0]);
            b = NumbersConverter.convertRomanToArabic(parser.operands[1]);
        }
        if (a < 1 || a > 10 || b < 1 || b > 10)
            throw new IllegalArgumentException("Ошибка: операнды должны находиться в диапазоне [1, 10]");
        int result;
        switch (parser.op) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> result = a / b;
            default -> throw new IllegalStateException("Ошибка: недопустимый знак операции");
        }

        System.out.println("Output:");
        if (parser.arabic)
            System.out.println(result);
        else {
            String resultRoman = NumbersConverter.convertArabicToRoman(result);
            System.out.println(resultRoman);
        }
    }
}
