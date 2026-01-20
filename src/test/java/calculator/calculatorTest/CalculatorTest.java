package calculator.calculatorTest;

import calculator.calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @DisplayName("양수 리스트를 더하면 올바른 합을 반환한다.")
    @Test
    void addPositiveNumbersInList() {
        // given(준비)
        final Calculator calculator = new Calculator();
        final List<Long> list = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        final long expected = 6L;

        // when(실행)
        final long actual = calculator.add(list);

        // then(검증)
        assertEquals(expected, actual);
    }
}