package org.iproduct.invoicing.view;

import org.iproduct.invoicing.model.Unit;

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
                        result = inputLong(fc).intValue();
                        method = instance.getClass().getMethod(methodName.toString(), Integer.class);
                        method.invoke(instance, result);
                        break;
                    case LONG:
                        result = inputLong(fc);
                        method = instance.getClass().getMethod(methodName.toString(), Long.class);
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
                    case UNIT:
                        result = inputUnit(fc);
                        method = instance.getClass().getMethod(methodName.toString(), Unit.class);
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

    public static Unit inputUnit(FieldConfig config) {
        String answer;
        boolean error;
        do {
            error = false;
            StringBuilder enumValues = new StringBuilder();
            for (Unit val : Unit.values()) {
                enumValues.append(val.ordinal()).append(") ").append(val).append("; ");
            }
            System.out.printf("Enter %s in: %s" + (config.defaultValue != null || config.optional ? " [<Enter> for '%s']" : "") + ":",
                    config.label, enumValues.toString(), config.defaultValue);
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
            }

            // convert string answer -> int -> enum value
            try {
                int intAnswer = Integer.valueOf(answer);
                if (intAnswer < 0 || intAnswer >= Unit.values().length) {
                    System.out.printf("Invalid choice - should be a number [0, %d]", Unit.values().length - 1);
                    error = true;
                } else {
                    return Unit.values()[intAnswer];
                }
            } catch (NumberFormatException ex) {
                System.out.printf("The answer should be a valid integer number [0, %d]", Unit.values().length - 1);
                error = true;
            }
        } while (error);
        return null;
    }

    public static Long inputLong(FieldConfig config) {
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
                return Long.valueOf(answer);
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
        if (config.dateFormat != null) {
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

    public static boolean aswerYesNoQuestion(String question) {
        System.out.printf("%s [Yes or No, <Enter> for Yes]?", question);
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("No")) {
            return false;
        } else {
            return true;
        }
    }

    public static int inputInt(String question, int minVal, int maxVal) {
        int intAnswer = minVal - 1;
        do {
            System.out.printf("%s [%d - %d]: ", question, minVal, maxVal);
            String answer = sc.nextLine();
            try {
                intAnswer = Integer.parseInt(answer);
            } catch (NumberFormatException ex) {
                System.out.printf("Invalid choice. Try Again.");
            }
        } while (intAnswer < minVal || intAnswer > maxVal);
        return intAnswer;
    }
}
