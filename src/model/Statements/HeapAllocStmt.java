package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.IntType;
import model.Types.RefType;
import model.Types.Type;
import model.Values.RefValue;
import model.Values.Value;

public class HeapAllocStmt implements IStmt{
    private Exp expression;
    private String varName;

    public HeapAllocStmt(Exp expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if(!symTable.contains_key(this.varName)) {
            throw new InterpreterException(this.varName + " is not defined in the symbol table!\n");
        }
        Value expValue = this.expression.eval(symTable, heap);
        RefValue refVal;
        if(symTable.get(this.varName) instanceof RefValue)
            refVal = ((RefValue)symTable.get(this.varName));
        else
            throw new InterpreterException(this.varName + " is not defined as a RefValue in the symbol table!\n");
        Type innerType = ((RefType)refVal.getType()).getInner();
        int firstAddr = heap.getFirstPos();
        heap.put(firstAddr,expValue);
        symTable.put(this.varName, new RefValue(firstAddr, innerType));
        return null;
    }

    @Override
    public String toString() {
        return "HeapAllocStmt("
                + this.expression + ", "
                + varName +
                ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typevar = typeEnv.get(varName);
        Type typexp = expression.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new InterpreterException("NEW stmt: right hand side and left hand side have different types ");
    }
}
