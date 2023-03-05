package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.RefType;
import model.Types.Type;
import model.Values.RefValue;
import model.Values.Value;

public class HeapWriteStmt implements IStmt{
    private Exp expression;
    private String varName;

    public HeapWriteStmt(Exp expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if(!symTable.contains_key(this.varName)){
            throw new InterpreterException("The key address: " + this.varName + " is not defined in the symTable!\n");
        }

        Value val = symTable.get(this.varName);
        if(!(val instanceof  RefValue))
            throw new InterpreterException("Value is not a RefValue!\n");
        int address = ((RefValue) val).getAddr();

        if(!heap.contains_key(address)){
            throw new InterpreterException("The given key address: " + address + " is not defined in the heap\n");
        }

        Value expVal = this.expression.eval(symTable, heap);

        heap.put(address, expVal);
        return null;
    }

    @Override
    public String toString() {
        return "HeapWriteStmt(" + expression + ", " + varName + ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typeVariable = typeEnv.get(varName);
        Type typeExpression = expression.typeCheck(typeEnv);
        if(typeVariable.equals(new RefType(typeExpression))){
            return typeEnv;
        }
        else
            throw new InterpreterException("HeapWriteStmt: Expression cannot be evaluated to " + typeExpression);
    }
}
