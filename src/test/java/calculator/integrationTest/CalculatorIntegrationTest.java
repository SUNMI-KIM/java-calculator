package calculator.integrationTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.Application;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorIntegrationTest extends NsTest {

    @DisplayName("유효한 문자열 입력 시, 전체 계산 흐름을 통해 올바른 최종 합계 결과를 출력한다")
    @ParameterizedTest(name = "{index}: input='{0}' → expected_sum={1}")
    @MethodSource("provideValidInputStringsAndExpectedSum")
    void should_calculate_and_output_correct_sum_for_valid_inputs(String inputString, String expectedSum) {

        assertSimpleTest(() -> {
            run(inputString);
            assertThat(output()).contains("결과 : " + expectedSum);
        });
    }

    private static Stream<Arguments> provideValidInputStringsAndExpectedSum() {
        return Stream.of(
                Arguments.of("\n", "0"),
                Arguments.of("5", "5"),
                Arguments.of("1,2,3", "6"),
                Arguments.of("1:2:3:4", "10"),
                Arguments.of("1,2:3", "6"),
                Arguments.of("//;\\n1:2:3", "6"),
                Arguments.of("//!\\n1!2!3!4!5", "15"),
                Arguments.of("10,20:30,40", "100")
        );
    }

    @DisplayName("유효하지 않은 문자열 입력 시, IllegalArgumentException을 던지고 결과는 출력되지 않는다")
    @ParameterizedTest(name = "{index}: invalid input='{0}'")
    @MethodSource("provideInvalidInputStrings")
    void should_throw_illegal_argument_exception_for_invalid_inputs(String invalidInput) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(invalidInput))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    private static Stream<Arguments> provideInvalidInputStrings() {
        return Stream.of(
                Arguments.of("//1\\n"),
                Arguments.of("//1;23"),
                Arguments.of("-1,2,3"),
                Arguments.of("1,a,3"),
                Arguments.of("1,,3"),
                Arguments.of("1,2,")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}