package calculator.calculator;

import java.util.List;

public class Calculator {
    public long add(List<Long> list) {
        return list.stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
