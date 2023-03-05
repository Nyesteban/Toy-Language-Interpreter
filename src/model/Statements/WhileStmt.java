package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.ADTs.MyIStack;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.BoolType;
import model.Types.Type;
import model.Values.BoolValue;
import model.Values.Value;

public class WhileStmt implements IStmt{
    private Exp cond;
    private IStmt body;

    public WhileStmt(Exp cond, IStmt body) {
        this.cond = cond;
        this.body = body;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIStack<IStmt> stack = state.getExecutionStack();
        MyIHeap<Integer,Value> heap = state.getHeap();
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        Value evalExp = this.cond.eval(symTable, heap);
        if(!(evalExp instanceof BoolValue))
            throw new InterpreterException("Condition cannot be evaluated to a bool value!");
        if(((BoolValue)evalExp).getValue())
        {
            stack.push(this);
            stack.push(this.body);
        }
        return null;
    }

    @Override
    public String toString() {
        return "while(" +
                cond +  ", " + body +
                ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typexp = cond.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            body.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new InterpreterException("The condition of WHILE has not the type bool");
    }
}
