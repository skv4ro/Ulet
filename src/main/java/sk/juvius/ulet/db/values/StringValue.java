package sk.juvius.ulet.db.values;

import sk.juvius.ulet.db.DataType;

public class StringValue extends AbstractValue<String> {

    public StringValue(String value) {
        super(value, DataType.STRING);
    }
}
