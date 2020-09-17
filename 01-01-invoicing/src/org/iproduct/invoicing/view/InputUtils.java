package org.iproduct.invoicing.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
//        String result = sc.nextLine();
//        System.out.println("You entered: " + result);
        for (FieldConfig fc : fieldConfigs) {
            Object result = null;
            switch (fc.type) {
                case INTEGER: result = inputInteger(fc); break;
                case DECIMAL: result = inputDouble(fc); break;
                case STRING: result = inputString(fc); break;
//                case DATE: result = inputDate(fc);
            }
            System.out.println("You entered: " + result);
        }
    }

    public static String inputString(FieldConfig config) {
        String answer;
        boolean error;
        do {
            error = false;
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']": "") + ":",
                    config.label, config.defaultValue);
            answer = sc.nextLine(); // read answer from console
            if (answer.isEmpty()) {
                if (config.defaultValue != null) {
                    return config.defaultValue;
                } else if (config.optional) {
                    return null;
                } else {
                    System.out.println("The field is mandatory, please enter a value.");
                    error = true;
                }
            } else { // answer is not empty - validation needed
                if (config.precision > 0 && answer.length() > config.precision) {
                    System.out.printf("The maximum number of characters is %s.%n", config.precision);
                    error = true;
                }
                if (config.regex != null && !Pattern.matches(config.regex, answer)) {
                    System.out.printf("The answer must match%s.%n", config.regex);
                    error = true;
                }
            }
        } while (error);
        return answer;
    }

    public static Integer inputInteger(FieldConfig config) {
        String answer;
        boolean error;
        do {
            error = false;
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']": "") + ":",
                    config.label, config.defaultValue);
            answer = sc.nextLine(); // read answer from console
            if (answer.isEmpty()) {
                if (config.defaultValue != null) {
                    answer = config.defaultValue;
                } else if (config.optional) {
                    return null;
                } else {
                    System.out.println("The field is mandatory, please enter a value.");
                    error = true;
                }
            } else { // answer is not empty - validation needed
                if (config.precision > 0 && answer.length() > config.precision) {
                    System.out.printf("The maximum number of characters is %s.%n", config.precision);
                    error = true;
                }
                if (config.regex != null && !Pattern.matches(config.regex, answer)) {
                    System.out.printf("The answer must match%s.%n", config.regex);
                    error = true;
                }
            }
            try {
                return Integer.valueOf(answer);
            } catch (NumberFormatException ex) {
                System.out.println("The answer should be a valid integer number.");
                error = true;
            }
        } while (error);
        return null;
    }

    public static Double inputDouble(FieldConfig config) {
        String answer;
        boolean error;
        Double result = null;
        do {
            error = false;
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']": "") + ":",
                    config.label, config.defaultValue);
            answer = sc.nextLine(); // read answer from console
            if (answer.isEmpty()) {
                if (config.defaultValue != null) {
                    answer = config.defaultValue;
                } else if (config.optional) {
                    return null;
                } else {
                    System.out.println("The field is mandatory, please enter a value.");
                    error = true;
                }
            } else { // answer is not empty - validation needed
                if (config.precision > 0 && answer.length() > config.precision) {
                    System.out.printf("The maximum number of characters is %s.%n", config.precision);
                    error = true;
                }
                if (config.regex != null && !Pattern.matches(config.regex, answer)) {
                    System.out.printf("The answer must match%s.%n", config.regex);
                    error = true;
                }
            }
            try {
                result = Double.valueOf(answer);
                if(config.fraction > 0) {
                    double power = Math.pow(10, config.fraction);
                    result = (Math.round(result * power)) / power;
                }
            } catch (NumberFormatException ex) {
                System.out.println("The answer should be a valid real number.");
                error = true;
            }
        } while (error);
        return result;
    }
}
