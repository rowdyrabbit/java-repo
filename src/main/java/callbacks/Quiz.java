package callbacks;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Quiz {

        interface QuestionSet {
            public void run(Result callback);
        }
        interface Result {
            public void result(Object answer);
            public void exception(Exception problem);
        }

        QuestionSet calculateIQ = new QuestionSet() {
            private final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            public void run(Result callback) {
                int iq = 100;
                try {
                    System.out.print("Is the sky blue  (y/n)?");
                    if ("y".equals(stdin.readLine())) iq += 5;
                    System.out.print("Is grass green (y/n)? ");
                    if ("y".equals(stdin.readLine())) iq += 5;
                    System.out.print("Is the world flat (y/n)? ");
                    if ("y".equals(stdin.readLine())) iq -= 50;
                    callback.result(new Integer(iq));
                } catch(java.io.IOException ex) {
                    callback.exception(ex);
                }
            }
        };
}
