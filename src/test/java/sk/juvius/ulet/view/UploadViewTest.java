package sk.juvius.ulet.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sk.juvius.ulet.R;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.ui.table.CellRenderer;
import sk.juvius.ulet.ui.table.ColumnInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UploadViewTest {

    private final List<ColumnInfo> columns = new ArrayList<>();
    private final List<UploadItem> data = new ArrayList<>();

    @BeforeEach
    void initMock() {
        columns.add(new ColumnInfo("File name", String.class, (row, col) -> data.get(row).getInstanceName()));
        columns.add(new ColumnInfo("Upload path", String.class, (row, col) -> data.get(row).getGenericName()));
        columns.add(new ColumnInfo("User name", User.class, (row, col) -> data.get(row).getFileVersion()));
//        columns.add(new ColumnInfo("Repo name", Repository.class, (row, col) -> data.get(row).getRepo()));
//        columns.add(new ColumnInfo("Upload image", Image.class, (row, col) -> data.get(row).getImage()));
//        data.add(new UploadItem("U1", "P1", new User(1, "skvarkaj", "hash1"), new Repository(1,"AS10","c:/skvarkaj"), R.getImage("asm.png")));
//        data.add(new UploadItem("Pavla Skvarkova", "P2", new User(1, "olesm", "hash2"), new Repository(1, "RP21",  "c:/olesm"), R.getImage("prt.png")));
//        data.add(new UploadItem("Petra Skvarkova, ml.", "P3", new User(1, "strakaf", "hash3"), new Repository(1, "CS01","c:/strakaf"), R.getImage("drw.png")));
    }

    @Disabled
    @Test
    void test() throws InterruptedException {
        UploadView uw = new UploadView();
        uw.getItemsTable().setData(columns, data);
        uw.getItemsTable().setDefaultRenderer(User.class, new CellRenderer(uw.getItemsTable().getGridColor()) {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(value instanceof User) {
                    User user = (User) value;
                    component.setText(user.getName());
                    component.setToolTipText(user.getHash());
                }
                return component;
            }
        });
        uw.getItemsTable().setDefaultRenderer(Repository.class, new CellRenderer(uw.getItemsTable().getGridColor()) {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(value instanceof Repository) {
                    Repository repo = (Repository) value;
                    component.setText(repo.getDisplayName());
                    component.setToolTipText(repo.getPath());
                }
                return component;
            }
        });
        uw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        new Thread(() -> SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uw.getItemsTable().getModel().addColumn(new ColumnInfo("LOL", String.class, null));
            uw.setVisible(false);
        })).start();
        uw.setVisible(true);
        assertEquals(6, uw.getItemsTable().getModel().getColumnCount());
    }
}
