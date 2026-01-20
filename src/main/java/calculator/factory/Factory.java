package calculator.factory;

import calculator.calculator.Calculator;
import calculator.check.Check;
import calculator.facade.Facade;
import calculator.io.InputOutput;
import calculator.parser.Parser;

public class Factory {

    private Factory() {
    }

    public static Facade createCalculatorFacade() {
        final InputOutput io = new InputOutput();
        final Check check = new Check();
        final Parser parser = new Parser();
        final Calculator calculator = new Calculator();

        return new Facade(io, check, parser, calculator);
    }
}
