package sk.juvius.ulet.cmds;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcAssembly.Assembly;
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

public class ModelCollector {

    private final Session session;
    private final SelectionOptions selOptions;
    private final Utilities utils;

    public ModelCollector(Session session) throws jxthrowable {
        this.session = session;
        utils = new Utilities(session);
        selOptions = pfcSelect.SelectionOptions_Create("prt_or_asm");
        selOptions.SetMaxNumSels(-1);
    }

    public List<ModelDescriptor> collectSingleModels() throws jxthrowable {
        return collectModels(false);
    }

    public List<ModelDescriptor> collectAllModels() throws jxthrowable {
        return collectModels(true);
    }

    private List<ModelDescriptor> collectModels(boolean all) throws jxthrowable {
        if(c.curProject == null) {
            session.UIShowMessageDialog("There is no project selected!", null);
            XCancelProEAction.Throw();
        }

        Model curModel = session.GetCurrentModel();
        if(curModel == null) XCancelProEAction.Throw();

        List<ModelDescriptor> list = new ArrayList<>();

        if(curModel.GetType() == ModelType.MDL_PART) {
            list.add(curModel.GetDescr());
        } else if(curModel.GetType() == ModelType.MDL_ASSEMBLY) {
            showSelectionMessage();
            Selections selections = session.Select(selOptions, null);
            for(int i = 0; i < selections.getarraysize(); i++) {
                Model selectedModel = selections.get(i).GetSelModel();
                if(selectedModel == null) continue;
                if(all) {
                    addAllWithinModel(selectedModel, list);
                } else {
                    list.add(selectedModel.GetDescr());
                }
            }
        }

        return list;
    }

    private void addAllWithinModel(Model model, List<ModelDescriptor> list) throws jxthrowable {
        list.add(model.GetDescr());

        if(model.GetType() == ModelType.MDL_ASSEMBLY) {
            final List<ModelDescriptor> newList = new ArrayList<>();
            utils.listActiveComponents((Assembly) model, newList);
            list.addAll(newList);
        }
    }

    private void showSelectionMessage() {
        try {
            session.UIDisplayMessage(c.MSG_MSG, "select_prompt", null);
        } catch(jxthrowable ignored) {
        }
    }
}
