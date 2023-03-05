package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.ADTs.MyIList;
import model.ADTs.MyIStack;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.BoolType;
import model.Types.Type;
import model.Values.*;

public class IfStmt implements IStmt{
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;
    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}
    @Override
    public String toString(){ return "(IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+"))";}
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIStack<IStmt> stack = state.getExecutionStack();
        MyIHeap<Integer,Value> heap = state.getHeap();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();

        Value conditionalExpressionVal = exp.eval(symbolTable, heap);
        if (((BoolValue) conditionalExpressionVal).getValue()) {
            stack.push(thenS);
        } else {
            stack.push(elseS);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typexp = exp.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typeCheck(typeEnv.copy());
            elseS.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new InterpreterException("The condition of IF has not the type bool");
    }
}
