package calculator.facade;

import calculator.calculator.Calculator;
import calculator.check.Check;
import calculator.io.InputOutput;
import calculator.parser.Parser;
import java.util.List;

public class Facade {

    private final InputOutput inputOutput;
    private final Check check;
    private final Parser parser;
    private final Calculator calculator;

    public Facade(
            final InputOutput inputOutput,
            final Check check,
            final Parser parser,
            final Calculator calculator) {
        this.inputOutput = inputOutput;
        this.check = check;
        this.parser = parser;
        this.calculator = calculator;
    }

    public void run() {
        final String inputString = inputOutput.input();
        check.checkFormat(inputString);

        final String[] regExpAndNumber = parser.parseRegExpNumber(inputString);
        final String regExp = regExpAndNumber[0];
        final String numString = regExpAndNumber[1];

        check.checkNumberFormat(numString, regExp);

        final List<Long> numList = parser.parseNumber(numString, regExp);
        inputOutput.output(calculator.add(numList));
    }
}
