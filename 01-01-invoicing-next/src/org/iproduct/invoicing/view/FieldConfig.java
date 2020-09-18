package org.iproduct.invoicing.view;

import static org.iproduct.invoicing.view.FieldType.DATE;
import static org.iproduct.invoicing.view.FieldType.STRING;

public class FieldConfig {
    public final String property;
    public final String label;
    public final FieldType type;
    public String defaultValue;
    public boolean optional;
    public int precision = 0;
    public int fraction = 0;
    public String regex = null;
    public String dateFormat;

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

    public FieldConfig(String property, String label, String defaultValue, FieldType type, String format) {
        this.property = property;
        this.label = label;
        this.defaultValue = defaultValue;
        this.type = type;
        if(type == DATE) {
            this.dateFormat = format;
        } else {
            this.regex = format;
        }
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
