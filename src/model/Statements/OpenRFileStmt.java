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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt{

    private Exp filePath;

    public OpenRFileStmt(Exp filePath) {
        this.filePath = filePath;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        model.ADTs.MyIDictionary<String, Value> symTbl = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader > fileTbl = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        Value filePathValue = filePath.eval(symTbl, heap);
        if(!(filePathValue instanceof StringValue))
        {
            throw new InterpreterException("openRFile evaluation failed");
        }
        if(fileTbl.contains_key((StringValue) filePathValue)){
            throw new InterpreterException("The filepath is already a key in FileTable");
        }
        try {
            BufferedReader fileBuffer = new BufferedReader(new FileReader(((StringValue) filePathValue).getValue()));
            fileTbl.put((StringValue) filePathValue, fileBuffer);
        }
        catch (FileNotFoundException ex){
            throw new InterpreterException(ex.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "OpenRFileStmt(" + this.filePath + ")";
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typeExpression = filePath.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType())){
            throw new InterpreterException("OpenRFileStmt: file path should be a stringValue!");
        }
        return typeEnv;
    }
}
