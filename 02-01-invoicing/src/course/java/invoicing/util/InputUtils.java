package course.java.invoicing.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);
    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
        for(FieldConfig fc: fieldConfigs) {
            StringBuilder setterName = new StringBuilder(fc.property);
            setterName.setCharAt(0, Character.toUpperCase(setterName.charAt(0)));
            setterName.insert(0, "set");
            Object result = null;
            Method method;
            try {
                switch(fc.type) {
                    case INTEGER:
                        Long longResult = inputLong(fc);
                        result = (longResult != null) ? longResult.intValue(): null;
                }
            }

        }
    }

    // Implementation helper methods
    private static Long inputLong(FieldConfig fc) {
        String answer;
        boolean error;
        do {
            error = false;
            System.out.printf("Input %s" +
                    (fc.optional || fc.defaultValue != null? "[<Enter> for %s]": "") + ":",
                    fc.label, fc.defaultValue);
            answer = sc.nextLine();
            if(answer.isEmpty()) {
                if(fc.defaultValue != null) {
                    answer = fc.defaultValue;
                } else if(fc.optional){
                    return null;
                }
            }

        } while(error);
    }

}
