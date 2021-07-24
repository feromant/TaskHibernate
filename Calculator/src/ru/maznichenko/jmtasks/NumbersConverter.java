package ru.maznichenko.jmtasks;

import java.util.HashMap;
import java.util.Map;

public class NumbersConverter {

    public static int convertRomanToArabic(String romanNumeral) {
        Map<Character, Integer> romanCharDict = new HashMap<>();
        romanCharDict.put('I', 1);
        romanCharDict.put('V', 5);
        romanCharDict.put('X', 10);

        int res = romanCharDict.get(romanNumeral.charAt(0));
        for (int i = 1; i < romanNumeral.length(); i += 1) {
            int curSymbol = romanCharDict.get(romanNumeral.charAt(i));
            int prevSymbol= romanCharDict.get(romanNumeral.charAt(i - 1));
            if (curSymbol <= prevSymbol)
                res += curSymbol;
            else
                res += curSymbol - 2 * prevSymbol;
        }
        return res;
    }

    public static String convertArabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " вне диапазона (0,100]");
        }
        int[] romanValueList = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanCharList = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < romanValueList.length; i += 1) {
            while (number >= romanValueList[i]){
                number -= romanValueList[i];
                res.append(romanCharList[i]);
            }
        }
        return res.toString();
    }
}