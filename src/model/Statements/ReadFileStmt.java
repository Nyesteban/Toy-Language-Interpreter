package model.Statements;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.Expressions.Exp;
import model.ProgramState.PrgState;
import model.Types.IntType;
import model.Types.StringType;
import model.Types.Type;
import model.Values.IntValue;
import model.Values.StringValue;
import model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{

    private Exp filePath;
    private String var_name;

    public ReadFileStmt(Exp filePath, String var_name) {
        this.filePath = filePath;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        model.ADTs.MyIDictionary<String, Value> symTbl = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        if(!symTbl.contains_key(var_name))
            throw new InterpreterException("The variable is not defined in the symbol table");
        if(!(symTbl.get(var_name) instanceof IntValue))
            throw new InterpreterException("The variable is not an int value in the symbol table");
        Value filePathValue = filePath.eval(symTbl, heap);
        if(!(filePathValue instanceof StringValue))
        {
            throw new InterpreterException("openRFile evaluation failed");
        }
        if(!fileTbl.contains_key((StringValue) filePathValue)){
            throw new InterpreterException("The filepath is not a key in FileTable");
        }
        try
        {
            BufferedReader fileBuffer = fileTbl.get((StringValue) filePathValue);
            String line = fileBuffer.readLine();
            if(line==null)
            {
                symTbl.put(var_name,new IntValue());
            }
            else
            {
                symTbl.put(var_name, new IntValue(Integer.parseInt(line)));
            }
        }
        catch(IOException e)
        {
            throw new InterpreterException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "ReadFileStmt(" + filePath + ')';
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typeVariable = typeEnv.get(var_name);
        Type typeExpression = this.filePath.typeCheck(typeEnv);
        if(typeVariable.equals(new IntType())){
            if(typeExpression.equals(new StringType())){
                return typeEnv;
            }
            else
                throw new InterpreterException("ReadFileStatement: file path be a stringValue!\n");
        }
        else
            throw new InterpreterException("ReadFileStatement: " + var_name + " is not an integer!\n");
    }
}
