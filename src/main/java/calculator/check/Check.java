package calculator.check;

public class Check {

    public void checkFormat(String inputString) {
        if (!inputString.matches("(//[^0-9]*\\\\n)?((\\d.)*\\d)?")) {
            throw new IllegalArgumentException();
        }
    }

    public void checkNumberFormat(String inputString, String regExp) {
        regExp = "((\\d" + regExp + "*)*\\d)?";
        if (!inputString.matches(regExp)) {
            throw new IllegalArgumentException();
        }
    }
}