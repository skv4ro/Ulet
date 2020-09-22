package sk.juvius.ulet.util.tabledialog.columns;

import com.ptc.pfc.pfcModel.ModelType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sk.juvius.ulet.c;
import sk.juvius.ulet.util.tabledialog.Record;

public class TypeColumn extends TableColumn<Record, ModelType> {

    private final Tooltip prtTooltip = new Tooltip("Part");
    private final Tooltip asmTooltip = new Tooltip("Assembly");
    private final Tooltip drwTooltip = new Tooltip("Drawing");
    private final Tooltip unknownTooltip = new Tooltip("???");
    private final Image prtImage = c.loadResImage("prt.png");
    private final Image asmImage = c.loadResImage("asm.png");
    private final Image drwImage = c.loadResImage("drw.png");

    public TypeColumn() {
        setText("Type");
        setCellValueFactory(new PropertyValueFactory<>("type"));
        setCellFactory(param -> new TypeCell());
        setMaxWidth(40);
        setStyle("-fx-alignment: TOP_CENTER");
    }

    class TypeCell extends TableCell<Record, ModelType> {
        @Override
        protected void updateItem(ModelType item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
                setStyle("");
            } else {
                if(item.equals(ModelType.MDL_PART)) {
                    setGraphic(new ImageView(prtImage));
                    setTooltip(prtTooltip);
                } else if(item.equals(ModelType.MDL_ASSEMBLY)) {
                    setGraphic(new ImageView(asmImage));
                    setTooltip(asmTooltip);
                } else if(item.equals(ModelType.MDL_DRAWING)) {
                    setGraphic(new ImageView(drwImage));
                    setTooltip(drwTooltip);
                } else {
                    setGraphic(new Text("?"));
                    setTooltip(unknownTooltip);
                }
            }
        }
    }
}
