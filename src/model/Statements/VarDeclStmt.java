package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ProgramState.PrgState;
import model.Types.*;
import model.Values.IntValue;
import model.Values.RefValue;
import model.Values.StringValue;
import model.Values.Value;

public class VarDeclStmt implements IStmt{
    private String name;
    private Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return typ.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        if(symTable.contains_key(name))
        {
            throw new InterpreterException("Variable" + name + "is already declared!");
        }
        else if(typ.equals(new IntType()))
        {
            symTable.put(name, new IntValue());
        }
        else if(typ.equals(new BoolType()))
        {
            symTable.put(name, new IntValue());
        }
        else if(typ.equals(new StringType()))
        {
            symTable.put(name, new StringValue());
        }
        else if(typ instanceof RefType refTyp)
        {
            symTable.put(name, new RefValue(refTyp.getInner()));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        typeEnv.put(name,typ);
        return typeEnv;
    }
}
