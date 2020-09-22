package sk.juvius.ulet.commands;

import com.ptc.pfc.pfcCommand.UICommand;

public interface Command {
    public UICommand getSystemCmd();

    public String getName();

    public boolean isDesignated();

    public String getCmdName();

    public void build(String msgFile);
}
