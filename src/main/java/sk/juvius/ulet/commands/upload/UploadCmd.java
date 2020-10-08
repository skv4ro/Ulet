package sk.juvius.ulet.commands.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSelect.SelectionOptions;
import com.ptc.pfc.pfcSelect.Selections;
import com.ptc.pfc.pfcSelect.pfcSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.NeedRepoCommand;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.util.Utilities;
import sk.juvius.ulet.view.UploadView;

import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;

public class UploadCmd extends NeedRepoCommand {

    private final static Logger log = LoggerFactory.getLogger(UploadCmd.class);

    private final SelectionOptions selOptions;

    public UploadCmd(AppContext appContext) throws jxthrowable {
        super(appContext);
        selOptions = pfcSelect.SelectionOptions_Create("prt_or_asm");
        selOptions.SetMaxNumSels(1);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        try {
            run();
        } catch (NullPointerException npe) {
            log.error("NPE " + Utilities.stackTraceToString(npe));
        } catch (Exception e) {
            log.error(Utilities.stackTraceToString(e));
        }
    }

    @Override
    public void run() throws jxthrowable {
        if(!privileged()) return;
        List<ModelDescriptor> toUpload = new ArrayList<>();
        if (curModel.GetType().getValue() == ModelType._MDL_PART) {
            toUpload.add(curModel.GetDescr());
        } else if (curModel.GetType().getValue() == ModelType._MDL_ASSEMBLY) {
            Selections selections = session.Select(selOptions, null);
            toUpload.add(selections.get(0).GetSelModel().GetDescr());
        }
        List<UploadItem> data = new ArrayList<>();
        for (ModelDescriptor md : toUpload) {
            data.add(new UploadItem(md));
        }
        UploadView uv = new UploadView();
        uv.getItemsTable().setData(data);
        TableColumn tc = uv.getItemsTable().getColumnModel().getColumn(0);
        if(tc != null) {
            tc.setMaxWidth(15);
            tc.setPreferredWidth(15);
            tc.setMaxWidth(100);
        }
        uv.setVisible(true);
    }
}
