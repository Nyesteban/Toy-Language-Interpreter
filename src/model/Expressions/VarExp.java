package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.Type;
import model.Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws InterpreterException {
        return tbl.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        return typeEnv.get(id);
    }
}
