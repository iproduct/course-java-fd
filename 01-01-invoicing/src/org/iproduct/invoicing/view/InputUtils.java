package org.iproduct.invoicing.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.iproduct.invoicing.view.FieldType.STRING;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static class FieldConfig {
        public final String property;
        public final String label;
        public final FieldType type;
        public String defaultValue;
        public boolean optional;
        public int precision = 0;
        public int fraction = 0;
        public String regex = null;

        public FieldConfig(String property, String label) {
            this.property = property;
            this.label = label;
            this.type = STRING;
        }

        public FieldConfig(String property, String label, boolean optional) {
            this.property = property;
            this.label = label;
            this.type = STRING;
            this.optional = optional;
        }

        public FieldConfig(String property, String label, String defaultValue) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = STRING;
        }

        public FieldConfig(String property, String label, String defaultValue, String regex) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = STRING;
            this.regex = regex;
        }

        public FieldConfig(String property, String label, String defaultValue, int length) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = STRING;
            this.precision = length;
        }

        public FieldConfig(String property, String label, FieldType type, int precision) {
            this.property = property;
            this.label = label;
            this.type = type;
            this.precision = precision;
        }

        public FieldConfig(String property, String label, String defaultValue, FieldType type, int precision) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = type;
            this.precision = precision;
        }

        public FieldConfig(String property, String label, FieldType type, int precision, int fraction) {
            this.property = property;
            this.label = label;
            this.type = type;
            this.precision = precision;
            this.fraction = fraction;
        }

        public FieldConfig(String property, String label, String defaultValue, FieldType type, int precision, int fraction) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = type;
            this.precision = precision;
            this.fraction = fraction;
        }

        public FieldConfig(String property, String label, String defaultValue, FieldType type, String regex) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = type;
            this.regex = regex;
        }

        public FieldConfig(String property, String label, String defaultValue, FieldType type, int precision, int fraction, String regex) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = type;
            this.precision = precision;
            this.fraction = fraction;
            this.regex = regex;
        }
    }

    public static void inputInstance(List<FieldConfig> fieldConfigs, Object instance) {
//        String result = sc.nextLine();
//        System.out.println("You entered: " + result);
        for (FieldConfig fc : fieldConfigs) {
            Object result = null;
            switch (fc.type) {
//                case INTEGER: result = inputInteger(fc);
//                case DECIMAL: result = inputDecimal(fc);
                case STRING:
                    result = inputString(fc);
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
            System.out.printf("Enter %s" + (config.defaultValue != null ? " [<Enter> for '%s']": "") + ":",
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
}
