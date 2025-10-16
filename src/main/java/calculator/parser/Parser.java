package calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public List<Long> parseNumber(String inputString, String regExp) {
        return Arrays.stream(inputString.split(regExp))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }
}