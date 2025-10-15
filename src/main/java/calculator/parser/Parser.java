package calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public List<Long> parseNumber(String inputString, String regExp) {
        return Arrays.stream(inputString.split(regExp))
                .map(s -> {
                    long l = Long.parseLong(s);
                    if (l < 0) {
                        throw new IllegalArgumentException();
                    }
                    return l;
                })
                .collect(Collectors.toList());
    }
}