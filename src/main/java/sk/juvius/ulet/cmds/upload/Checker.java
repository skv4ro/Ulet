package sk.juvius.ulet.cmds.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.juvius.ulet.c;
import sk.juvius.ulet.utils.tabledialog.Record;
import sk.juvius.ulet.utils.tabledialog.TableDialog;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

class Checker {

    private TableDialog dialog;
    private Checkable checker;

    Checker(Uploader uploader) {
        try {
            int index = c.checkerPath.indexOf("?");
            File checkerPath = new File(c.checkerPath.substring(0, index));
            String checkerClass = c.checkerPath.substring(index + 1);
            URL[] urls = new URL[]{checkerPath.toURI().toURL()};
            URLClassLoader loader = new URLClassLoader(urls, c.class.getClassLoader());
            Class clazz = loader.loadClass(checkerClass);
            checker = (Checkable) clazz.newInstance();
            dialog = new TableDialog(uploader);
        } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            checker = null;
            dialog = new TableDialog(uploader);
            e.printStackTrace(System.out);
        }
    }

    void showDialog() {
        dialog.setVisible(true);
    }

    void hideDialog() {
        dialog.setVisible(false);
    }

    boolean check(Session session, Map<String, ModelDescriptor> items) throws jxthrowable {
        if (checker == null) {
            MessageDialogOptions mdo = pfcUI.MessageDialogOptions_Create();
            mdo.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
            session.UIShowMessageDialog("Checker not available!", mdo);
            return false;
        }
        CheckContainer container = new CheckContainer(c.curProject, c.curUser, items);
        List<Record> records = checker.checkItems(session, container);
        dialog.setItems(FXCollections.observableArrayList(records));
        dialog.setProjectAndUserLabel(c.curProject, c.curUser);
        return true;
    }

    void updateItems(Map<String, ModelDescriptor> items) {
        ObservableList<Record> list = dialog.getItems();
        list.forEach(record -> {
            if(!record.getUpload()) {
                String fileName = record.getName() + "." + stringModelType(record.getType());
                items.remove(fileName);
            }
        });
    }

    private String stringModelType(ModelType modelType) {
        if(modelType.equals(ModelType.MDL_PART)) return "prt";
        else if(modelType.equals(ModelType.MDL_ASSEMBLY)) return "asm";
        else if(modelType.equals(ModelType.MDL_DRAWING)) return "drw";
        else return null;
    }

    boolean shouldContinue() {
        boolean result = dialog.isAllowed();
        dialog.setAllowed(false);
        return result;
    }
}
