package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.IntType;
import model.Types.RefType;
import model.Types.Type;
import model.Values.RefValue;
import model.Values.Value;

public class HeapReadExp implements Exp{
    private Exp expression;

    public HeapReadExp(Exp expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws InterpreterException {
        Value expValue = this.expression.eval(tbl, hp);
        if(!(expValue instanceof RefValue))
            throw new InterpreterException("Expression could not be evaluated to a RefValue!\n");
        int address = ((RefValue)expValue).getAddr();
        if(!hp.contains_key(address))
            throw new InterpreterException("Address could not be found in the heap!");
        return hp.get(address);
    }

    @Override
    public String toString() {
        return "HeapReadExp(" +
                expression +
                ')';
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typ = expression.typeCheck(typeEnv);
        if (typ instanceof RefType reft) {
            return reft.getInner();
        } else
            throw new InterpreterException("the rH argument is not a Ref Type");
    }
}
