package ru.maznichenko.jmtasks;

import static ru.maznichenko.jmtasks.NumberType.*;

public class Parser {

    public InputData parseInput (String str) {
        str = str.replaceAll(" ", ""); // удаляем пробелы
        str = str.toUpperCase(); // преобразуем к верхнему регистру

        String romanNum  = "(V?I{0,3}|I[VX]|X)"; // регулярное выражение для римских чисел от I до X
        String arabNum = "\\d+"; // регулярное выражение для арабских чисел
        String operation = "[+\\-*/]"; // регулярное выражение для знаков операций +, -, * и /

        InputData data = new InputData();

        if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/")) {
            data.setOperands(str.split(operation)); // разбиваем выражение на операнды
            data.setOp(str.charAt(data.getOperands()[0].length())); // определяем знак операции

            if (data.getOperands()[0].matches(arabNum) && data.getOperands()[1].matches(arabNum))
                data.setType(ARABIC);
            else if (data.getOperands()[0].matches(romanNum) && data.getOperands()[1].matches(romanNum))
                data.setType(ROMAN);
            else throw new IllegalArgumentException("Ошибка: неверный формат входных данных");
        }
        else throw new IllegalStateException("Ошибка: недопустимый знак операции");
        return data;
    }
}
enum NumberType {ARABIC, ROMAN}

class InputData {
    private String[] operands;
    private char op;
    private NumberType type;

    public String[] getOperands() {
        return operands;
    }

    public void setOperands(String[] operands) {
        this.operands = operands;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    public NumberType getType() {
        return type;
    }

    public void setType(NumberType type) {
        this.type = type;
    }
}

