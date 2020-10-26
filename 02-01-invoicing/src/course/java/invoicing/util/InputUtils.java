package course.java.invoicing.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
        for (FieldConfig fc : fieldConfigs) {
            StringBuilder setterName = new StringBuilder(fc.property);
            setterName.setCharAt(0, Character.toUpperCase(setterName.charAt(0)));
            setterName.insert(0, "set");
            Object result = null;
            Method method;
//            try {
                switch (fc.type) {
                    case INTEGER:
                        Long longResult = inputLong(fc);
                        result = (longResult != null) ? longResult.intValue() : null;
                }
//            } catch ()

        }
    }

    // Implementation helper methods
    private static Long inputLong(FieldConfig fc) {
        String answer;
        boolean error;
        do {
            error = false;
            System.out.printf("Input %s" +
                            (fc.optional || fc.defaultValue != null ? "[<Enter> for %s]" : "") + ":",
                    fc.label, fc.defaultValue);
            answer = sc.nextLine();
            if (answer.isEmpty()) {
                if (fc.defaultValue != null) {
                    answer = fc.defaultValue;
                } else if (fc.optional) {
                    return null;
                } else {
                    System.out.printf("The fields '%s' is mandatory, please enter a value.%n", fc.label);
                    error = true;
                    continue;
                }
            } else { //the answer is not empty
                if (fc.presicion > 0 && answer.length() > fc.presicion) {
                    System.out.printf("The maximum number of characters is %d.%n", fc.presicion);
                    error = true;
                    continue;
                }
                if (fc.regex != null && !Pattern.matches(fc.regex, answer)) {
                    System.out.printf("The answer must match regular expression '%s'.%n", fc.regex);
                    error = true;
                    continue;
                }
            }
            try {
                return Long.parseLong(answer);
            } catch (NumberFormatException ex) {
                System.out.printf("The answer must be a valid integer number.%n");
                error = true;
            }
        } while (error);
        return null; //should be never reached
    }

}
