package en.artembert.testRunner;

import en.artembert.testRunner.tests.FailedTest;
import en.artembert.testRunner.tests.SuccessfulTest;

public class Main {
    public static void main(String[] args) {
        Runner.run(SuccessfulTest.class);
        Runner.run(FailedTest.class);
    }
}
