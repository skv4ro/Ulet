package sk.juvius.ulet.cmds.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.util.tabledialog.Record;

import java.util.List;

public interface Checkable {
    List<Record> checkItems(Session session, CheckContainer container) throws jxthrowable;
}
