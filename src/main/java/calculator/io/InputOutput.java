package calculator.io;

import camp.nextstep.edu.missionutils.Console;

public class InputOutput {

    public String input() {
        return Console.readLine();
    }

    public void output(Long input) {
        System.out.println("결과 : " + input);
    }
}
