package sk.juvius.ulet.cmds.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcAssembly.Assembly;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcExceptions.XCancelProEAction;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSelect.SelectionOptions;
import com.ptc.pfc.pfcSelect.Selections;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.c;
import sk.juvius.ulet.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class UploadWhole extends DefaultUICommandActionListener {

    private final Session session;
    private final Utilities utils;
    private final SelectionOptions selOptions;
    private final List<ModelDescriptor> toProcess = new ArrayList<>();
    private final Uploader uploader;

    public UploadWhole(Session session) throws jxthrowable {
        this.session = session;
        this.utils = new Utilities(session);
        this.uploader = new Uploader(session);
        selOptions = pfcSelect.SelectionOptions_Create("prt_or_asm");
        selOptions.SetMaxNumSels(-1);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        if(c.curProject == null) {
            session.UIShowMessageDialog("There is no project selected!", null);
            return;
        }

        Model curModel = session.GetCurrentModel();
        if(curModel == null) XCancelProEAction.Throw();

        toProcess.clear();

        if(curModel.GetType() == ModelType.MDL_PART) {
            toProcess.add(curModel.GetDescr());
        } else if(curModel.GetType() == ModelType.MDL_ASSEMBLY) {
            showSelectionMessage();
            Selections selections = session.Select(selOptions, null);
            for(int i = 0; i < selections.getarraysize(); i++) {
                Model selectedModel = selections.get(i).GetSelModel();
                addAllWithinModel(selectedModel);
            }
        }

        uploader.upload(toProcess);
        session.GetCurrentWindow().Repaint();
    }

    private void addAllWithinModel(Model model) throws jxthrowable {
        toProcess.add(model.GetDescr());

        if(model.GetType() == ModelType.MDL_ASSEMBLY) {
            final List<ModelDescriptor> list = new ArrayList<>();
            utils.listActiveComponents((Assembly) model, list);
            toProcess.addAll(list);
        }
    }

    private void showSelectionMessage() {
        try {
            session.UIDisplayMessage(c.MSG_MSG, "select_prompt", null);
        } catch(jxthrowable ignored) {
        }
    }
}
