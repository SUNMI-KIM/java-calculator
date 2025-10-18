package calculator;

import calculator.facade.Facade;
import calculator.factory.Factory;

public class Application {
    public static void main(String[] args) {

        Facade facade = Factory.createCalculatorFacade();
        facade.run();
    }
}
