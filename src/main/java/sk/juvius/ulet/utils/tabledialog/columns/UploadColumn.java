package sk.juvius.ulet.utils.tabledialog.columns;

import com.ptc.pfc.pfcModel.ModelType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import sk.juvius.ulet.c;
import sk.juvius.ulet.utils.tabledialog.Record;

public class UploadColumn extends TableColumn<Record, Boolean> {

    private final Image uploadImage = c.loadResImage("upload.png");
    private final Image stopImage = c.loadResImage("stop.png");
    private final Tooltip uploadTooltip = new Tooltip("Will be uploaded");
    private final Tooltip stopTooltip = new Tooltip("Will NOT be uploaded");

    public UploadColumn() {
        setText("Upload");
        setCellValueFactory(new PropertyValueFactory<>("upload"));
        setCellFactory(param -> new UploadCell());
        setMaxWidth(55);
        setStyle("-fx-alignment: TOP_CENTER");
    }

    class UploadCell extends TableCell<Record, Boolean> {
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
                setStyle("");
            } else {
                if (item) {
                    setGraphic(new ImageView(uploadImage));
                    setTooltip(uploadTooltip);
                } else {
                    setGraphic(new ImageView(stopImage));
                    setTooltip(stopTooltip);
                }
                setOnMouseClicked(event -> {
                    if(event.getButton().equals(MouseButton.PRIMARY) && !event.isControlDown() && !event.isShiftDown()) {
                        Record record = (Record) getTableRow().getItem();
                        if (record != null) {
                            if(record.getUpload()) {
                                record.setUpload(false);
                            } else {
                                record.setUpload(true);
                            }

                            this.getTableView().getItems().forEach(record1 -> {
                                if(record.getType().equals(ModelType.MDL_DRAWING)) {
                                    if(record1.getName().equals(record.getName())) record1.setUpload(record.getUpload());
                                } else {
                                    if(record1.getName().equals(record.getName()) && record1.getType().equals(ModelType.MDL_DRAWING)) record1.setUpload(record.getUpload());
                                }
                            });
                        }
                    }
                });
            }
        }
    }
}
