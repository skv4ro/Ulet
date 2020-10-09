package sk.juvius.ulet.ui.table.filter;

import org.apache.commons.lang3.StringUtils;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.ui.table.DynamicTableModel;

import javax.swing.*;

public class StringRowFilter extends RowFilter<DynamicTableModel<UploadItem>, Integer> {

    private final String originalSearchText;
    private final FilterEngine engine;

    public StringRowFilter(String originalSearchText, FilterEngine engine) {
        this.originalSearchText = originalSearchText;
        this.engine = engine;
    }

    public StringRowFilter(String originalSearchText) {
        this(originalSearchText, FilterEngine.CASCADE);
    }

    @Override
    public boolean include(Entry<? extends DynamicTableModel<UploadItem>, ? extends Integer> entry) {
        if(engine == FilterEngine.CASCADE) {
            return filterCascade(entry);
        }
        if(engine == FilterEngine.WHOLE) {
            return filterWhole(entry);
        }
        return true;
    }

    private boolean filterCascade(Entry<? extends DynamicTableModel<UploadItem>, ? extends Integer> entry) {
        String  text = StringUtils.stripAccents(this.originalSearchText);
        text = text.toLowerCase();
        String[] tokens = text.split(" ");
        int hit = 0;
        for(int i = 0; i < entry.getValueCount(); i++) {
            Object value = entry.getValue(i);
            String strValue = entry.getModel().getColumn(i).getResolverStringValue(entry.getIdentifier(), i);
            if(value != null) {
                for(String token : tokens) {
                    if(token.equals("$")) {
                        hit++;
                    } else if(token.startsWith("$") && token.length() > 1) {
                        if(strValue.contains(token)) hit++;
                    } else {
                        if(strValue.contains(token)) hit++;
                    }
                }
            }
        }
        return hit == tokens.length;
    }

    private boolean filterWhole(Entry<? extends DynamicTableModel<UploadItem>, ? extends Integer> entry) {
        String  text = StringUtils.stripAccents(this.originalSearchText);
        text = text.toLowerCase();
        String[] tokens = text.split(" ");
        boolean include = false;
        for(int i = 0; i < entry.getValueCount(); i++) {
            Object value = entry.getValue(i);
            String strValue = entry.getModel().getColumn(i).getResolverStringValue(entry.getIdentifier(), i);
            if(value != null) {
                for(String token : tokens) {
                    if(strValue.startsWith("$")) {
                        if(token.startsWith("$")) {
                            if(strValue.contains(token)) include = true;
                        }
                    } else {
                        if(strValue.contains(token)) include = true;
                    }
                }
            }
        }
        return include;
    }
}

//TODO upravit aby bralo viac escaped tokenov napr 405 $prt $asm v CASCADE vratilo aj prt aj asm
//TODO upravit aby ignorovalo $ ked je osamote
