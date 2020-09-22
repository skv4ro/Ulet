package sk.juvius.ulet.db.values;

import sk.juvius.ulet.db.DataType;

public class IntegerValue extends AbstractValue<Integer> {

    public IntegerValue(int value) {
        super(value, DataType.INTEGER);
    }
}
