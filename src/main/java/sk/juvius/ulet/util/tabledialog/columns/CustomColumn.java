package sk.juvius.ulet.util.tabledialog.columns;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.juvius.ulet.util.tabledialog.Record;

public class CustomColumn extends TableColumn<Record, String> {

    public CustomColumn() {
        setText("Custom");
        setCellValueFactory(new PropertyValueFactory<>("custom"));
        setCellFactory(param -> new CustomCell(this));
        setPrefWidth(215);
    }

    public void setTitle(String title) {
        setText(title);
    }

    class CustomCell extends TableCell<Record, String> implements Wrappable {

        private final CustomColumn column;

        CustomCell(CustomColumn column) {
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
            }
        }
    }
}
