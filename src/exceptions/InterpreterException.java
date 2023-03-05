package exceptions;

public class InterpreterException extends Exception {
    protected String msg;

    public InterpreterException() {
        this.msg = "ERROR";
    }

    public InterpreterException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
