package sk.juvius.ulet.util.tabledialog.columns;

import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import sk.juvius.ulet.util.tabledialog.Record;

interface Wrappable {
    default void wrap(TableCell<Record, String> cell, TableColumn column) {
        Text text = new Text();
        cell.setGraphic(text);
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(column.widthProperty());
        text.textProperty().bind(cell.itemProperty());
    }
}
