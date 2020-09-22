package sk.juvius.ulet.util.tabledialog.columns;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sk.juvius.ulet.util.tabledialog.Record;
import sk.juvius.ulet.util.tabledialog.StatusContainer;

public class StatusColumn extends TableColumn<Record, StatusContainer> {

    public StatusColumn() {
        setText(null);
        setCellValueFactory(new PropertyValueFactory<>("status"));
        setCellFactory(param -> new StatusCell());
        setMaxWidth(20);
        setStyle("-fx-alignment: TOP_CENTER");
    }

    class StatusCell extends TableCell<Record, StatusContainer> {
        @Override
        protected void updateItem(StatusContainer item, boolean empty) {
            super.updateItem(item, empty);

            if(item == null || empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
                setStyle("");
            } else {
                setGraphic(createCircle(item.getHighestPriority().getColor()));
                setTooltip(new Tooltip(item.getMsg()));
            }
        }
    }

    private Circle createCircle(Color color) {
        int circleRadius = 7;
        Circle circle = new Circle(circleRadius);
        circle.setFill(color);
        return circle;
    }
}


