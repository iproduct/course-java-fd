package course.java.invoicing.util;

import course.java.invoicing.exception.InvalidUserDataException;
import course.java.invoicing.model.Unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.util.logging.Level.SEVERE;

public class InputUtils {
    private static Logger LOG = Logger.getLogger("c.j.i.u.InputUtils");
    private static Scanner sc = new Scanner(System.in);

    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
        for (FieldConfig fc : fieldConfigs) {
            StringBuilder setterName = new StringBuilder(fc.property);
            setterName.setCharAt(0, Character.toUpperCase(setterName.charAt(0)));
            setterName.insert(0, "set");
            Object result = null;
            Method method;
            try {
                switch (fc.type) {
                    case INTEGER:
                        Long longResult = inputLong(fc);
                        result = (longResult != null) ? longResult.intValue() : null;
                        method = instance.getClass().getMethod(setterName.toString(), Integer.class);
                        method.invoke(instance, result);
                        break;
                    case LONG:
                        longResult = inputLong(fc);
                        result = (longResult != null) ? longResult: null;
                        method = instance.getClass().getMethod(setterName.toString(), Long.class);
                        method.invoke(instance, result);
                        break;
                    case DECIMAL:
                        Double doubleResult = inputDouble(fc);
                        result = (doubleResult != null) ? doubleResult.doubleValue() : null;
                        method = instance.getClass().getMethod(setterName.toString(), Double.class);
                        method.invoke(instance, result);
                        break;
                    case STRING:
                        result = inputString(fc);
                        method = instance.getClass().getMethod(setterName.toString(), String.class);
                        method.invoke(instance, result);
                        break;
                    case UNIT:
                        Unit unitResult = inputUnit(fc);
                        result = (unitResult != null) ? unitResult : null;
                        method = instance.getClass().getMethod(setterName.toString(), Integer.class);
                        method.invoke(instance, result);
                        break;
                    case DATE:
                        LocalDate dateResult = inputDate(fc);
                        result = (dateResult != null) ? dateResult : null;
                        method = instance.getClass().getMethod(setterName.toString(), Unit.class);
                        method.invoke(instance, result);
                        break;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                LOG.log(SEVERE,
                        String.format("Error accessing property '%s' on instance %s.", fc.property, instance.toString()), ex);
            }

        }
    }


    // Implementation helper methods
    private static Long inputLong(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidUserDataException e) {
                System.out.println(e.getMessage());
                error = true;
            }
            if (answer != null) {
                try {
                    return Long.parseLong(answer);
                } catch (NumberFormatException ex) {
                    System.out.printf("The answer must be a valid integer number.%n");
                    error = true;
                }
            }
        } while (error);
        return null;
    }

    private static Double inputDouble(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidUserDataException e) {
                System.out.println(e.getMessage());
                error = true;
            }
            if (answer != null) {
                try {
                    double result = Double.parseDouble(answer);
                    if (fc.fraction > 0) {
                        double powerOfTen = Math.pow(10, fc.fraction);
                        result = Math.round(result * powerOfTen) / powerOfTen;
                    }
                    return result;
                } catch (NumberFormatException ex) {
                    System.out.printf("The answer must be a valid integer number.%n");
                    error = true;
                }
            }
        } while (error);
        return null;
    }

    private static String inputString(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidUserDataException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);
        return answer;
    }

    private static Unit inputUnit(FieldConfig fc) {
        String answer = null;
        boolean error;
        StringBuilder enumValues = new StringBuilder();
        for (Unit val : Unit.values()) {
            enumValues.append(val.ordinal()).append(") ").append(val);
        }
        do {
            error = false;
            System.out.printf("Input %s in: %s" +
                            (fc.optional || fc.defaultValue != null ? "[<Enter> for %s]" : "") + ":",
                    fc.label, enumValues, fc.defaultValue);
            answer = sc.nextLine();
            if (answer.isEmpty()) {
                if (fc.defaultValue != null) {
                    answer = fc.defaultValue;
                } else if (fc.optional) {
                    return null;
                } else {
                    System.out.printf("The fields '%s' is mandatory, please enter a value.%n", fc.label);
                    error = true;
                }
            } else { //the answer is not empty
                if (fc.presicion > 0 && answer.length() > fc.presicion) {
                    System.out.printf("The maximum number of characters is %d.%n", fc.presicion);
                    error = true;
                }
                if (fc.regex != null && !Pattern.matches(fc.regex, answer)) {
                    System.out.printf("The answer must match regular expression '%s'.%n", fc.regex);
                    error = true;
                }
            }
            if (answer != null) {
                try {
                    return Unit.values()[Integer.parseInt(answer)];
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    System.out.printf("The answer must be a valid integer choice.%n");
                    error = true;
                }
            }
        } while (error);
        return null;
    }

    private static LocalDate inputDate(FieldConfig fc) {
        DateTimeFormatter dtf;
        if(fc.dateFormat != null) {
            dtf = DateTimeFormatter.ofPattern(fc.dateFormat);
        } else {
            dtf = DateTimeFormatter.ISO_DATE;
        }
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidUserDataException e) {
                System.out.println(e.getMessage());
                error = true;
            }
            if (answer != null) {
                try {
                    return LocalDate.parse(answer, dtf);
                } catch (NumberFormatException ex) {
                    System.out.printf("The answer must be a valid integer number.%n");
                    error = true;
                }
            }
        } while (error);
        return null;
    }

    private static String inputStringOrThrow(FieldConfig fc) throws InvalidUserDataException {
        String answer;
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
                throw new InvalidUserDataException(
                        String.format("The fields '%s' is mandatory, please enter a value.%n", fc.label));
            }
        } else { //the answer is not empty
            if (fc.presicion > 0 && answer.length() > fc.presicion) {
                throw new InvalidUserDataException(String.format(
                        "The maximum number of characters is %d.%n", fc.presicion));
            }
            if (fc.regex != null && !Pattern.matches(fc.regex, answer)) {
                throw new InvalidUserDataException(String.format(
                        "The answer must match regular expression '%s'.%n", fc.regex));
            }
        }
        return answer;
    }

}
