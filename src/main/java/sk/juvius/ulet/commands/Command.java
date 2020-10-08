package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;

public interface Command {
    UICommand getSystemCmd();

    String getName();

    boolean isDesignated();

    String getCmdName();

    void build(String msgFile);

    void run() throws jxthrowable;
}
