package sk.juvius.ulet.utils.tabledialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import sk.juvius.ulet.c;
import sk.juvius.ulet.cmds.upload.Uploader;
import sk.juvius.ulet.utils.tabledialog.columns.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TableDialogController implements Initializable {

    private TableDialog parent;
    @FXML
    private TableView<Record> table;
    @FXML
    private Button uploadBtn;
    @FXML
    private Button addSingleBtn;
    @FXML
    private Button addWholeBtn;
    @FXML
    private Button removeSingleBtn;
    @FXML
    private Button removeWholeBtn;
    @FXML
    private Label selectedLabel;
    @FXML
    private Label projectLabel;
    @FXML
    private Label userLabel;

    private Uploader uploader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        StatusColumn statusColumn = new StatusColumn();
        NameColumn nameColumn = new NameColumn();
        CustomColumn customColumn = new CustomColumn();
        TypeColumn typeColumn = new TypeColumn();
        VersionColumn versionColumn = new VersionColumn();
        UploadColumn uploadColumn = new UploadColumn();

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(statusColumn, nameColumn, customColumn, typeColumn, versionColumn, uploadColumn);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int size = table.getSelectionModel().getSelectedItems().size();
            String appendix;
            if (size == 1) {
                appendix = " item";
            } else {
                appendix = " items";
            }
            selectedLabel.setText(size + appendix);
        });

        uploadBtn.setGraphic(new ImageView(c.loadResImage("upload.png")));
    }

    TableView<Record> getTable() {
        return table;
    }

    @FXML
    public void handleUploadBtn() {
        parent.setAllowed(true);
        parent.setVisible(false);
    }

    void setParent(TableDialog parent) {
        this.parent = parent;
    }

    void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    public Label getProjectLabel() {
        return projectLabel;
    }

    public Label getUserLabel() {
        return userLabel;
    }
}
