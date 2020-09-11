package sk.juvius.ulet.utils.tabledialog;

public class Status {

    private String msg;
    private Priority priority;

    public Status(String msg, Priority priority) {
        this.msg = msg;
        this.priority = priority;
    }

    public String getMsg() {
        return msg;
    }

    public Priority getPriority() {
        return priority;
    }
}
