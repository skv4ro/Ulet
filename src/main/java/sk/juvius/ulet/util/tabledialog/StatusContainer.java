package sk.juvius.ulet.util.tabledialog;

import java.util.HashSet;

public class StatusContainer extends HashSet<Status> {

    private Priority highestPriority = Priority.NO;

    @Override
    public boolean add(Status status) {
        if(status == null) return true;
        Priority priority = status.getPriority();
        if (priority.getPriority() > highestPriority.getPriority()) highestPriority = priority;
        return super.add(status);
    }

    public Priority getHighestPriority() {
        return highestPriority;
    }

    public String getMsg() {
        StringBuilder msg = new StringBuilder();
        for(Status status : this) {
            msg.append(status.getMsg()).append("\n");
        }
        return msg.toString().trim();
    }

    @Override
    public String toString() {
        return Integer.toString(highestPriority.getPriority());
    }
}
