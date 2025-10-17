package calculator.parserTest;

import calculator.parser.Parser;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @DisplayName("유효한 숫자 문자열을 파싱하면 올바른 숫자 리스트를 반환한다")
    @Test
    void parseNumbers_WithValidString_ReturnsCorrectList() {
        // given(준비)
        final Parser parser = new Parser();
        final String inputString = "1;2,3";
        final String regExp = "[;,]";
        final long[] expected = {1, 2, 3};

        // when(실행)
        final List<Long> list = parser.parseNumber(inputString, regExp);
        final long[] actual = list.stream()
                .mapToLong(Long::longValue)
                .toArray();

        // then(검증)
        assertArrayEquals(expected, actual);
    }

    @DisplayName("유효한 문자열을 파싱하면 올바른 문자열을 반환한다")
    @Test
    void parseString_WithValidString_ReturnsCorrectString() {

        // given(준비)
        final Parser parser = new Parser();
        final String inputString = "123";
        final String expected = "[123,;]";

        // when(실행)
        final String actual = parser.parseRegExp(inputString);

        // then(검증)
        assertEquals(expected, actual);
    }

    @DisplayName("입력 문자열 유형 4가지를 모두 파싱하면 올바른 문자열 배열을 반환한다.")
    @ParameterizedTest(name = "{index}: input={0} → expected={1}")
    @MethodSource("provideInputStrings")
    void parseString_VariousCases_ReturnsExpected(String inputString, String[] expected) {
        // given(준비)
        final Parser parser = new Parser();

        // when(실행)
        final String[] actual = parser.parseRegExpNumber(inputString);

        // then(검증)
        assertArrayEquals(expected, actual);
    }

    // 테스트 케이스 제공 메서드
    private static Stream<Arguments> provideInputStrings() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("//;\\n1;2;3", new String[]{";", "1;2;3"}),
                org.junit.jupiter.params.provider.Arguments.of("//;\\n", new String[]{";", ""}),
                org.junit.jupiter.params.provider.Arguments.of("1,2:3", new String[]{"", "1,2:3"}),
                org.junit.jupiter.params.provider.Arguments.of("", new String[]{"", ""})
        );
    }
}