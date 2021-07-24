package ru.maznichenko.jmtasks;

public class Parser {
    public String[] operands;
    public char op;
    public boolean arabic;

    public void parseInput (String str) {
        str = str.replaceAll(" ", ""); // удаляем пробелы
        str = str.toUpperCase(); // преобразуем к верхнему регистру

        String romanNum  = "(V?I{0,3}|I[VX]|X)"; // регулярное выражение для римских чисел от I до X
        String arabNum = "\\d+"; // регулярное выражение для арабских чисел
        String operation = "[+\\-*/]"; // регулярное выражение для знаков операций +, -, * и /

        if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/")) {
            operands = str.split(operation); // разбиваем выражение на операнды
            op = str.charAt(operands[0].length()); // определяем знак операции

            if (operands[0].matches(arabNum) && operands[1].matches(arabNum))
                arabic = true;
            else if (operands[0].matches(romanNum) && operands[1].matches(romanNum))
                arabic = false;
            else throw new IllegalArgumentException("Ошибка: неверный формат входных данных");
        }
        else throw new IllegalStateException("Ошибка: недопустимый знак операции");
    }
}
