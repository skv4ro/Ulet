package sk.juvius.ulet.ui.table;

public class ColumnInfo {
    private final Class<?> clazz;
    private final String name;
    private ValueResolver resolver;

    public ColumnInfo(String name, Class<?> clazz, ValueResolver resolver) {
        this.clazz = clazz;
        this.name = name;
        this.resolver = resolver;
    }

    public ColumnInfo(String name, Class<?> clazz) {
        this(name, clazz, (row, col) -> null);
    }

    public Class<?> getColumnClass() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public ValueResolver getResolver() {
        return resolver;
    }

    public void setResolver(ValueResolver resolver) {
        this.resolver = resolver;
    }

    public Object getResolverValue(int row, int col) {
        if(resolver == null) return null;
        return resolver.getValue(row, col);
    }

    public String getResolverStringValue(int row, int col) {
        if(resolver == null) return null;
        return resolver.getString(row, col);
    }
}
