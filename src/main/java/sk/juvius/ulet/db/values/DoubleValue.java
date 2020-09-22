package sk.juvius.ulet.db.values;

import sk.juvius.ulet.db.DataType;

public class DoubleValue extends AbstractValue<Double> {

    public DoubleValue(Double value) {
        super(value, DataType.DOUBLE);
    }
}
