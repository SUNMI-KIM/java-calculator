package calculator.checkTest;

import calculator.check.Check;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckTest {

    @DisplayName("format에 맞지 않는 문자열이 들어올 경우 에러를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"//0bc\n", "//1:2:3", "\n1:2:3", "-1:2:3"})
    void throwsExceptionForInvalidFormat(String inputString) {

        // given
        final Check check = new Check();

        // when, then
        assertThrows(IllegalArgumentException.class, () -> check.checkFormat(inputString));
    }
}
