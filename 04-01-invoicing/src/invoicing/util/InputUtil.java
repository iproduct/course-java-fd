package invoicing.util;

import invoicing.exception.InvalidEntityDataException;
import invoicing.model.Product;
import invoicing.model.Unit;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static invoicing.model.Unit.PCS;
import static java.util.logging.Level.SEVERE;

public class InputUtil {
    private static Logger LOG = Logger.getLogger(InputUtil.class.getName());

    public static boolean inputInstance(InputStream in, List<FieldConfig> fieldConfigs, Object instance) {
        Scanner sc = new Scanner(in);
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
                        method = instance.getClass().getMethod(setterName.toString(), Unit.class);
                        method.invoke(instance, result);
                        break;
                    case DATE:
                        LocalDate dateResult = inputDate(fc);
                        result = (dateResult != null) ? dateResult : null;
                        method = instance.getClass().getMethod(setterName.toString(), LocalDate.class);
                        method.invoke(instance, result);
                        break;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                LOG.log(SEVERE,
                        String.format("Error accessing property '%s' on instance %s.", fc.property, instance.toString()), ex);
            }
        }
        return true;
    }


    // Implementation helper methods
    public static Long inputLong(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidEntityDataException e) {
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

    public static Double inputDouble(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidEntityDataException e) {
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

    public static String inputString(FieldConfig fc) {
        String answer = null;
        boolean error;
        do {
            error = false;
            try {
                answer = inputStringOrThrow(fc);
            } catch (InvalidEntityDataException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);
        return answer;
    }

    public static Unit inputUnit(FieldConfig fc) {
        String answer = null;
        boolean error;
        StringBuilder enumValues = new StringBuilder();
        for (Unit val : Unit.values()) {
            enumValues.append(val.ordinal()).append(")").append(val).append(", ");
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
                if (fc.precision > 0 && answer.length() > fc.precision) {
                    System.out.printf("The maximum number of characters is %d.%n", fc.precision);
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

    public static LocalDate inputDate(FieldConfig fc) {
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
            } catch (InvalidEntityDataException e) {
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

    public static String inputStringOrThrow(FieldConfig fc) throws InvalidEntityDataException {
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
                throw new InvalidEntityDataException(
                        String.format("The fields '%s' is mandatory, please enter a value.%n", fc.label));
            }
        } else { //the answer is not empty
            if (fc.precision > 0 && answer.length() > fc.precision) {
                throw new InvalidEntityDataException(String.format(
                        "The maximum number of characters is %d.%n", fc.precision));
            }
            if (fc.regex != null && !Pattern.matches(fc.regex, answer)) {
                throw new InvalidEntityDataException(String.format(
                        "The answer must match regular expression '%s'.%n", fc.regex));
            }
        }
        return answer;
    }


    public static boolean inputProduct(Product p) {
        do {
            System.out.print("Product code:");
            String ans = sc.nextLine();
            if(Pattern.matches("([A-Z]{2})(\\d{3})", ans)) {
                p.setCode(ans);
            } else {
                System.out.println("Invalid product code. Example code: BK001");
            }
        } while(p.getCode() == null);
        do {
            System.out.print("Product name:");
            String ans = sc.nextLine().trim();
            if(ans.length() > 1) {
                p.setName(ans);
            } else {
                System.out.println("Invalid product name. Should be at least two characters long.");
            }
        } while(p.getName() == null);
        do {
            System.out.print("Product description:");
            String ans = sc.nextLine().trim();
            if(ans.length() > 9) {
                p.setDescription(ans);
            } else {
                System.out.println("Invalid product description. Should be at least ten characters long.");
            }
        } while(p.getDescription() == null);
        do {
            System.out.print("Product price:");
            String ans = sc.nextLine();
            p.setPrice(-1D);
            try {
                p.setPrice(Double.parseDouble(ans));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price number format. Example: 32.50");
            }
            if(p.getPrice() < 0) {
                System.out.println("Invalid price - should be positive number.");
            }
        } while(p.getPrice() < 0);
        do{
            System.out.print(Arrays.stream(Unit.values())
                .map(unit -> (unit.ordinal() + 1) + ": " + unit)
                .collect(Collectors.joining(", ")) + " [Enter for PCS]: ");
            String ans = sc.nextLine().trim();
            if(ans.length() == 0) {
                p.setUnit(PCS);
                break;
            }
            int unitOrd = -1;
            try {
                unitOrd = Integer.parseInt(ans);
            } catch (NumberFormatException e) {}
            if(unitOrd < 1 || unitOrd > Unit.values().length) {
                System.out.println("Invalid unit choice. Try again.");
            } else {
                p.setUnit(Unit.values()[unitOrd  - 1]);
            }
        } while(p.getUnit() == null);
        return true;
    }
}
