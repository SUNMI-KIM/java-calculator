package calculator.calculatorTest;

import calculator.calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorTest {

    @DisplayName("계산기 리스트 덧셈 테스트")
    @Test
    void calculatorAddTest() {
        // given(준비)
        final Calculator calculator = new Calculator();
        final List<Long> list = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

        // when(실행)
        final long answer = calculator.add(list);

        // then(검증)
        assertEquals(6L, answer);
    }
}
