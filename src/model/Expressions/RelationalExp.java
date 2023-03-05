package model.Expressions;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Types.BoolType;
import model.Types.IntType;
import model.Types.Type;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.Value;

public class RelationalExp implements Exp{

    private Exp expression1, expression2;

    private int operation; // 1 - '<'; 2 - '<='; 3 - '=='; 4 - '!='; 5 - '>'; 6 - '>='

    public RelationalExp(Exp expression1, Exp expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer, Value> hp) throws InterpreterException {
        Value value1 = null, value2 = null;
        value1 = expression1.eval(table,hp);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.eval(table,hp);
            if (value2.getType().equals(new IntType())) {
                IntValue integer1 = (IntValue) value1;
                IntValue integer2 = (IntValue) value2;
                int number1, number2;
                number1 = integer1.getValue();
                number2 = integer2.getValue();
                if (operation == 1) {
                    return new BoolValue(number1 < number2);
                }
                else if (operation == 2) {
                    return new BoolValue(number1 <= number2);
                }
                else if (operation == 3) {
                    return new BoolValue(number1 == number2);
                }
                else if (operation == 4) {
                    return new BoolValue(number1 != number2);
                }
                else if (operation == 5) {
                    return new BoolValue(number1 > number2);
                }
                else if (operation == 6) {
                    return new BoolValue(number1 >= number2);
                }
                else
                {
                    throw new InterpreterException("Invalid operator");
                }
            } else {
                throw new InterpreterException("Second operand is not an integer");
            }
        }
        throw new InterpreterException("First operand is not an integer");
    }

    @Override
    public String toString() {
        String operationString;
        if(operation==1)
            operationString="<";
        else if(operation==2)
            operationString="<=";
        else if(operation==3)
            operationString="==";
        else if(operation==4)
            operationString="!=";
        else if(operation==5)
            operationString=">";
        else if(operation==6)
            operationString=">=";
        else
            operationString="?";
        return expression1.toString() + operationString + expression2.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new InterpreterException("second operand is not an integer");
        } else
            throw new InterpreterException("first operand is not an integer");
    }
}
