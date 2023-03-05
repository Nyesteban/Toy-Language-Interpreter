package view;

import controller.Controller;
import exceptions.InterpreterException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.completeExecution(); }
        catch (InterpreterException e)
        {
            System.out.println(e.toString());
        }
    }

    public Controller getController() {
        return ctr;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
