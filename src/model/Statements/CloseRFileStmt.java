package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.StringType;
import model.Types.Type;
import model.Values.StringValue;
import model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt{
    private Exp filePath;

    public CloseRFileStmt(Exp filePath) {
        this.filePath = filePath;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        model.ADTs.MyIDictionary<String, Value> symTbl = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        Value filePathValue = filePath.eval(symTbl, heap);
        if(!(filePathValue instanceof StringValue))
        {
            throw new InterpreterException("closeRFile evaluation failed");
        }
        if(!fileTbl.contains_key((StringValue) filePathValue)){
            throw new InterpreterException("The filepath is not a key in FileTable");
        }
        BufferedReader fileBuffer = fileTbl.get((StringValue) filePathValue);
        try
        {
        fileBuffer.close();
        fileTbl.remove((StringValue) filePathValue);
        }
        catch(IOException e)
        {
            throw new InterpreterException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "CloseRFileStmt(" + filePath + ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typeExpression = filePath.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType())){
            throw new InterpreterException("CloseRFileStmt: file path should be a stringValue!");
        }
        return typeEnv;
    }
}
