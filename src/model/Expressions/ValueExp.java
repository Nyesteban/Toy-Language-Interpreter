package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.Type;
import model.Values.Value;

public class ValueExp implements Exp{
    private Value value;

    public ValueExp(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws InterpreterException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        return this.value.getType();
    }
}
