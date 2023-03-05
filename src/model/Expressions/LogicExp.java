package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.BoolType;
import model.Types.Type;
import model.Values.*;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws InterpreterException {
        Value firstVal = this.e1.eval(tbl,hp);
        if (firstVal.getType().equals(new BoolType())) {
            boolean firstBool = ((BoolValue) firstVal).getValue();
            Value secondVal = this.e2.eval(tbl,hp);
            if (secondVal.getType().equals(new BoolType())) {
                boolean secondBool = ((BoolValue) secondVal).getValue();

                if (op == 1) {
                    return new BoolValue(firstBool && secondBool);
                }
                if (op == 2) {
                    return new BoolValue(firstBool || secondBool);
                } else {
                    throw new InterpreterException("Invalid operator");
                }
            }
            else
            {
                throw new InterpreterException("Second operand is not a bool");
            }
        } else {
            throw new InterpreterException("First operand is not a bool");
        }
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new InterpreterException("second operand is not a bool");
        } else
            throw new InterpreterException("first operand is not a bool");
    }
}
