package sk.juvius.ulet.view;

import static sk.juvius.ulet.AppContext.APP_NAME;
import sk.juvius.ulet.R;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.ui.Dialog;
import sk.juvius.ulet.ui.Panel;
import sk.juvius.ulet.ui.table.ColumnInfo;
import sk.juvius.ulet.ui.table.Table;
import sk.juvius.ulet.util.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ArrayList;

public class UploadView extends Dialog {

    private final JLabel userLabel = new JLabel("<html><b>user:</b> UNKNOWN</html>");
    private final JLabel repoLabel = new JLabel("<html><b>repository:</b> UNKNOWN</html>");
    private final JTextField searchTextField = new JTextField();
    private final Table<UploadItem> itemsTable = new Table<>();
    private final JLabel selectedLabel = new JLabel("selected: 0");
    private final JButton uploadButton = new JButton("upload");
    private final JButton addButton = new JButton();
    private final JButton removeButton = new JButton();

    public UploadView() {
        initColumns();
        setTitle(APP_NAME + " upload");
        setIconImage(R.getImage("upload.png"));
        sk.juvius.ulet.ui.Panel content = new Panel();
        int spacing = 2;
        Insets insets = new Insets(spacing, spacing, spacing, spacing);

        JScrollPane scrollPane = new JScrollPane(itemsTable);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.insets = insets;
        buttonGbc.fill = GridBagConstraints.VERTICAL;
        buttonGbc.weighty = 1;
        buttonPanel.add(addButton, buttonGbc);
        buttonGbc.gridx++;
        buttonPanel.add(removeButton, buttonGbc);

        scrollPane.setPreferredSize(new Dimension(512, 256));
        scale(uploadButton, 1.5);
        scrollPane.setBorder(null);
        addButton.setIcon(new ImageIcon(R.getImage("add.png").getScaledInstance(12,12, 1)));
        removeButton.setIcon(new ImageIcon(R.getImage("remove.png").getScaledInstance(12,12,1)));
        addButton.setPreferredSize(new Dimension(24, 24));
        removeButton.setPreferredSize(new Dimension(24, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.insets = insets;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        content.add(userLabel, gbc);
        gbc.gridy++;
        content.add(repoLabel, gbc);
        gbc.gridy++;
        content.add(searchTextField, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        content.add(scrollPane, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        content.add(selectedLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        content.add(buttonPanel, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        content.add(uploadButton, gbc);

        add(content);
        pack();
        setLocationRelativeTo(null);
    }

    public void setData(List<UploadItem> data) {
        itemsTable.setData(data);
    }

    public Table<UploadItem> getItemsTable() {
        return itemsTable;
    }

    private void scale(Component component, double scale) {
        Dimension dim = component.getPreferredSize();
        double width = dim.width;
        double height = dim.height;
        int newWidth = (int) (width * scale);
        int newHeight = (int) (height * scale);
        component.setPreferredSize(new Dimension(newWidth, newHeight));
    }

    private void initColumns() {
        itemsTable.getColumns().add(new ColumnInfo("Type", Image.class, (row, col) -> Utilities.getModelTypeImage(itemsTable.getData().get(row).getType())));
        itemsTable.getColumns().add(new ColumnInfo("Instance name", String.class, (row, col) -> itemsTable.getData().get(row).getInstanceName()));
        itemsTable.getColumns().add(new ColumnInfo("Generic name", User.class, (row, col) -> itemsTable.getData().get(row).getGenericName()));
        itemsTable.getColumns().add(new ColumnInfo("File version", Integer.class, (row, col) -> itemsTable.getData().get(row).getFileVersion()));
        itemsTable.getColumns().add(new ColumnInfo("Path", String.class, (row, col) -> itemsTable.getData().get(row).getPath()));
    }
}

//TODO dat data a columns do table a nie do zdedeneho oneho a upravit setData metodu
