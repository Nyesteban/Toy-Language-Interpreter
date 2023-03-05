package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.*;
import model.Expressions.*;
import model.ProgramState.PrgState;
import model.Types.Type;
import model.Values.*;

public class AssignStmt implements IStmt{
    private String id;
    private Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString(){ return id+"="+ exp.toString();}

    @Override
    public PrgState execute(PrgState state) throws InterpreterException{
        MyIDictionary<String,Value> symTbl= state.getSymbolTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        if (symTbl.contains_key(id))
        {
            Value val = exp.eval(symTbl,heap);
            Type typId = (symTbl.get(id)).getType();
            //if ((val.getType()).equals(typId))
                symTbl.put(id, val);
            //else throw new InterpreterException("declared type of variable "+id+" and type of the assigned expression do not match");
        }
        else throw new InterpreterException("the used variable" +id + " was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typevar = typeEnv.get(id);
        Type typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new InterpreterException("Assignment: right hand side and left hand side have different types ");
    }
}
