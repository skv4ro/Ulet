package sk.juvius.ulet.utils.tabledialog.columns;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.juvius.ulet.utils.tabledialog.Record;

public class NameColumn extends TableColumn<Record, String> {

    public NameColumn() {
        setText("Name");
        setCellValueFactory(new PropertyValueFactory<>("name"));
        setCellFactory(param -> new NameCell(this));
        setPrefWidth(215);
    }

    class NameCell extends TableCell<Record, String> implements Wrappable {

        private final NameColumn column;

        NameCell(NameColumn column) {
            this.column = column;
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if(item == null || empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
                setStyle("");
            } else {
                wrap(this, column);

                TableRow row = this.getTableRow();
                if (row == null) {
                    setTooltip(new Tooltip());
                } else {
                    Record record = (Record) row.getItem();
                    if (record != null) {
                        setTooltip(new Tooltip(record.getPath()));
                    }
                }
            }
        }
    }
}
