package en.calculator;

public class Summator {
    private int counter = 0;
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;

    // !!! сигнатуру метода менять нельзя
    public void calc(Data data) {
        counter++;
        if (counter % 100_000 == 0) {
            counter = 0;
        }
        sum += data.getValue();

        sumLastThreeValues = data.getValue() + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = data.getValue();

        for (var idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (data.getValue() + 1) - sum);
            someValue = Math.abs(someValue) + counter;
        }
    }

    public int getSum() {
        return sum;
    }

    public int getPrevValue() {
        return prevValue;
    }

    public int getPrevPrevValue() {
        return prevPrevValue;
    }

    public int getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public int getSomeValue() {
        return someValue;
    }
}
