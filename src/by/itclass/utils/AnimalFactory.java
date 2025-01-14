package by.itclass.utils;

import by.itclass.exception.CompetitionException;
import by.itclass.model.Animal;

public class AnimalFactory {

    public static final String CHIP_REGEX =  "(?=\\d{15}\\b)(?:112|643)09(?:81|56)\\d{8}";
    public static Animal getInstance(String textString) throws CompetitionException {
        var lexemes = textString.split("[,;]");
        try {
            var chipNumber = Long.parseLong(validateByRegex(lexemes[0], CHIP_REGEX));
        } catch (IllegalStateException e) {
            throw new CompetitionException(e, textString);
        }
    }

    private static String validateByRegex(String value, String regex) {
        if (value.matches(regex)) {
            return value;
        }
        throw new IllegalStateException("Chip number or email is invalid");
    }
    private static String validateByEmpty(String value) {
        if (!value.isEmpty()) {
            return value;
        }
        throw new IllegalStateException("Name or breed is empty");
    }
}
