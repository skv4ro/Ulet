package sk.juvius.ulet.exceptions;

public class RootNotInCPException extends Exception {
    @Override
    public String getMessage() {
        return "Class path for root of the application is not set in protk.dat!";
    }
}
