package sk.juvius.ulet.mock;

import com.ptc.pfc.pfcModel.ModelType;
import sk.juvius.ulet.model.UploadItem;

import java.util.ArrayList;
import java.util.List;

public class UploadItemMock {
    public static List<UploadItem> getData() {
        List<UploadItem> data = new ArrayList<>();
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "405-4-0101-001", null, 3, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "405-4-0101-001", null, 5, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "405-4-0101-002", null, 30, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "405-4-0101-002", null, 12, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "405-5-01-003", null, 10, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "405-5-01-003", null, 5, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_ASSEMBLY, "405-4-0101", null, 36, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "405-4-0101", null, 2, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "448-5-010201-005", null, 1, "c:/repo2"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "448-5-010201-005", null, 1, "c:/repo2"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "448-8-0201-004", null, 6, "c:/repo1"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_DRAWING, "448-8-0201-004", null, 6, "c:/repo"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_PART, "-8-as-pl", null, 1, "c:/repo2"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_ASSEMBLY, "-8-as-pl", null, 1, "c:/repo2"));
        data.add(new sk.juvius.ulet.model.UploadItem(ModelType.MDL_ASSEMBLY, "-8-as-pl_", null, 1, "c:/repo2"));
        return data;
    }
}
