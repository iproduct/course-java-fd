package invoicing.util;

import static invoicing.util.FieldType.DATE;

public class FieldConfig {
    public final String property;
    public final String label;
    public FieldType type = FieldType.STRING;
    public String defaultValue = null;
    public boolean optional = false;
    public int precision = 0;
    public int fraction =0;
    public String regex = null;
    public String dateFormat = null;

    public FieldConfig(String property, String label) {
        this.property = property;
        this.label = label;
    }

    public FieldConfig(String property, String label, boolean optional) {
        this.property = property;
        this.label = label;
        this.optional = optional;
    }

    public FieldConfig(String property, String label, boolean optional, String regex) {
        this.property = property;
        this.label = label;
        this.optional = optional;
        this.regex = regex;
    }

    public FieldConfig(String property, String label, String defaultValue, String regex) {
        this.property = property;
        this.label = label;
        this.defaultValue = defaultValue;
        this.regex = regex;
    }

    public FieldConfig(String property, String label, String defaultValue, int presicion) {
        this.property = property;
        this.label = label;
        this.defaultValue = defaultValue;
        this.precision = presicion;
    }

    public FieldConfig(String property, String label, FieldType type, int presicion) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.precision = presicion;
    }

    public FieldConfig(String property, String label, FieldType type, String defaultValue) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public FieldConfig(String property, String label, FieldType type, String defaultValue, int presicion) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
        this.precision = presicion;
    }

    public FieldConfig(String property, String label, FieldType type, int presicion, int fraction) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.precision = presicion;
        this.fraction = fraction;
    }

    public FieldConfig(String property, String label, FieldType type, String defaultValue, int presicion, int fraction) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
        this.precision = presicion;
        this.fraction = fraction;
    }

    public FieldConfig(String property, String label, FieldType type, String defaultValue, String format) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
        if(type == DATE) {
            this.dateFormat = format;
        } else {
            this.regex = format;
        }
    }

    public FieldConfig(String property, String label, FieldType type, String defaultValue, int presicion, int fraction, String regex) {
        this.property = property;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
        this.precision = presicion;
        this.fraction = fraction;
        this.regex = regex;
    }

}
