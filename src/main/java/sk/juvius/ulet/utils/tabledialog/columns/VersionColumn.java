package sk.juvius.ulet.utils.tabledialog.columns;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.juvius.ulet.utils.tabledialog.Record;

public class VersionColumn extends TableColumn<Record, Integer> {

    public VersionColumn() {
        setText("Version");
        setCellValueFactory(new PropertyValueFactory<>("version"));
        setMaxWidth(55);
    }
}
