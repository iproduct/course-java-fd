package org.iproduct.invoicing.view;

import java.util.List;
import java.util.Scanner;

import static org.iproduct.invoicing.view.FieldType.STRING;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static class FieldConfig {
        public final String property;
        public final String label;
        public final FieldType type;
        public final String defaultValue;
        public int precision = 10;
        public int fraction = 0;
        public String regex = null;

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
        public FieldConfig(String property, String label, String defaultValue, FieldType type, int precision) {
            this.property = property;
            this.label = label;
            this.defaultValue = defaultValue;
            this.type = type;
            this.precision = precision;
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
    public static void inputString(List<FieldConfig> fieldConfigs, Object instance) {
        String result = sc.nextLine();
        System.out.println("You entered: " + result);
    }
}
