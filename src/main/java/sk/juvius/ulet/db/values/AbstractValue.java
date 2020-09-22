package sk.juvius.ulet.db.values;

import sk.juvius.ulet.db.DataType;

public abstract class AbstractValue<T> implements Value {

    protected final DataType dataType;
    protected final T value;

    protected AbstractValue(T value, DataType dataType) {
        this.dataType = dataType;
        this.value = value;
    }

    public DataType getDataType() {
        return dataType;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "dataType=" + dataType +
                ", value=" + value +
                '}';
    }
}
