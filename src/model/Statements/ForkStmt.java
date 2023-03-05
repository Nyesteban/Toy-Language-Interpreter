package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.ADTs.MyIStack;
import model.ADTs.MyStack;
import model.ProgramState.PrgState;
import model.Types.Type;
import model.Values.Value;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIStack<IStmt> newStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        MyIDictionary<String, Value> newDict = symTable.copy();
        return new PrgState(newStack, newDict, state.getFileTable(), state.getOutput(), state.getHeap(), PrgState.generateNewID(), this.stmt);
    }

    @Override
    public String toString() {
        return "fork(" + stmt + ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        stmt.typeCheck(typeEnv.copy());
        return typeEnv;
    }
}
