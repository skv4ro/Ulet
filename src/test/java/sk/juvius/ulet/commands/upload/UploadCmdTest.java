package sk.juvius.ulet.commands.upload;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sk.juvius.ulet.mock.UploadItemMock;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.ui.table.DynamicTableModel;
import sk.juvius.ulet.ui.table.filter.StringRowFilter;
import sk.juvius.ulet.view.UploadView;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

class UploadCmdTest {
    @Disabled
    @Test
    void testView() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UploadView uv = new UploadView();
        uv.setData(UploadItemMock.getData());
        TableRowSorter<DynamicTableModel<UploadItem>> sorter = new TableRowSorter<>(uv.getItemsTable().getModel());
        uv.getItemsTable().setRowSorter(sorter);
        uv.getSearchTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = uv.getSearchTextField().getText().toLowerCase();
                if(text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(new StringRowFilter(text));
                }
            }
        });
        sorter.setComparator(1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        uv.setVisible(true);
    }
}
