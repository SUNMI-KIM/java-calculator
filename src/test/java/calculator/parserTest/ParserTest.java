package calculator.parserTest;

import calculator.parser.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @DisplayName("음수 문자열을 파싱하면 에러를 발생시킨다")
    @Test
    void parseNumbers_WithNegativeString_ThrowsException() {
        // given(준비)
        final Parser parser = new Parser();
        final String inputString = "1;2,-3";
        final String regExp = "[;,]";

        // when(실행), then(검증)
        assertThrows(IllegalArgumentException.class,
                () -> parser.parseNumber(inputString, regExp));
    }
}