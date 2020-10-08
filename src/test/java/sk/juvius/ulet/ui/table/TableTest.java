package sk.juvius.ulet.ui.table;

import com.ptc.pfc.pfcModel.ModelType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import sk.juvius.ulet.R;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class TableTest {
    private final List<ColumnInfo> columns = new ArrayList<>();
    private final List<UploadItem> data = new ArrayList<>();

    @BeforeEach
    void initMock() {
        columns.add(new ColumnInfo("Model type", Image.class, (row, col) -> resolveModelType(data.get(row).getType())));
        columns.add(new ColumnInfo("Instance name", String.class, (row, col) -> data.get(row).getInstanceName()));
        columns.add(new ColumnInfo("Generic name", User.class, (row, col) -> data.get(row).getGenericName()));
        columns.add(new ColumnInfo("File version", Integer.class, (row, col) -> data.get(row).getFileVersion()));
        columns.add(new ColumnInfo("Path", String.class, (row, col) -> data.get(row).getPath()));
        data.add(new UploadItem(ModelType.MDL_DRAWING, "405-4-0101-001", null, 5, "c:/skvarkaj"));
        data.add(new UploadItem(ModelType.MDL_ASSEMBLY, "405-4-0101", "null", 2, "c:/skvarkaj"));
        data.add(new UploadItem(ModelType.MDL_PART, "405-4-0101-001", null, 1, "c:/skvarkaj"));
    }

    @Disabled
    @Test
    void test() throws InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Table<UploadItem> table = new Table<>();
        table.setData(columns, data);
        JDialog frame = new JDialog();
        frame.setModal(true);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private Image resolveModelType(ModelType type) {
        Image image = null;
        if(type.getValue() == ModelType._MDL_ASSEMBLY) image = R.getImage("asm.png");
        if(type.getValue() == ModelType._MDL_PART) image = R.getImage("prt.png");
        if(type.getValue() == ModelType._MDL_DRAWING) image = R.getImage("drw.png");
        return image;
    }
}
