package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ProgramState.PrgState;
import model.Types.Type;

public interface IStmt {
        PrgState execute(PrgState state) throws InterpreterException;
        //which is the execution method for a statement.
        MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws InterpreterException;
}
