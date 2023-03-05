package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIList;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.Type;
import model.Values.Value;

public class PrintStmt implements IStmt{
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "print(" +exp.toString()+")";
    }

    public PrgState execute(PrgState state) throws InterpreterException {
        MyIList<Value> ot = state.getOutput();
        ot.add(exp.eval(state.getSymbolTable(), state.getHeap()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}

