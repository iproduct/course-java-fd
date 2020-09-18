package org.iproduct.invoicing.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
        for (FieldConfig fc : fieldConfigs) {
            StringBuilder methodName = new StringBuilder(fc.property);
            methodName.setCharAt(0, Character.toTitleCase(methodName.charAt(0)));
            methodName.insert(0, "set");
            Object result = null;
            Method method;
            try {
                switch (fc.type) {
                    case INTEGER:
                        result = inputInteger(fc);
                        method = instance.getClass().getMethod(methodName.toString(), Integer.class);
                        method.invoke(instance, result);
                        break;
                    case DECIMAL:
                        result = inputDouble(fc);
                        method = instance.getClass().getMethod(methodName.toString(), Double.class);
                        method.invoke(instance, result);
                        break;
                    case STRING:
                        result = inputString(fc);
                        method = instance.getClass().getMethod(methodName.toString(), String.class);
                        method.invoke(instance, result);
                        break;
                    case DATE:
                        result = inputDate(fc);
                        method = instance.getClass().getMethod(methodName.toString(), LocalDate.class);
                        method.invoke(instance, result);
                        break;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("You entered: " + result);
        }
    }

    public static String inputString(FieldConfig config) {
        String answer;
        boolean error;
        do {
            error = false;
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']" : "") + ":",
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
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']" : "") + ":",
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
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']" : "") + ":",
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
                if (config.fraction > 0) {
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

    public static LocalDate inputDate(FieldConfig config) {
        DateTimeFormatter dtf;
        if(config.dateFormat != null) {
            dtf = DateTimeFormatter.ofPattern(config.dateFormat);
        } else {
            dtf = DateTimeFormatter.ISO_DATE;
        }
        String answer;
        boolean error;
        LocalDate result = null;
        do {
            error = false;
            System.out.printf("Enter %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']" : "") + ":",
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
                result = LocalDate.parse(answer, dtf);
            } catch (DateTimeParseException ex) {
                System.out.printf("The answer should be a valid date in format: '%s'.", config.dateFormat);
                error = true;
            }
        } while (error);
        return result;
    }


}
