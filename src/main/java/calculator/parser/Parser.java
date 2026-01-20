package calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public List<Long> parseNumber(String inputString, String regExp) {

        if (inputString.isEmpty()) {
            return List.of(0L);
        }

        return Arrays.stream(inputString.split(regExp))
            .mapToLong(Long::parseLong)
            .boxed()
            .collect(Collectors.toList());
    }

    public String parseRegExp(String inputString) {
        return "[" + inputString + ",:]";
    }

    public String[] parseRegExpNumber(String inputString) {

        if (inputString == null || inputString.isEmpty()) {
            return new String[]{"", ""};
        }

        if (inputString.startsWith("//")) {
            String[] parts = inputString.split("\\\\n");
            String regExpPart = parts[0].replace("//", "");
            String numberPart = parts.length > 1 ? parts[1] : "";
            return new String[]{parseRegExp(regExpPart), numberPart};
        }

        return new String[]{"[,:]", inputString};
    }
}