package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.Type;
import model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws InterpreterException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException;
}
