package callbacks;

public class QuizTest implements Quiz {
    public static void main(String[] args) {
        QuizTest test = new QuizTest();
        test.calculateIQ.run(new Quiz.Result() {
            public void result(Object answer) {
                System.out.println("Your IQ is " + answer);
            }

            public void exception(Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}