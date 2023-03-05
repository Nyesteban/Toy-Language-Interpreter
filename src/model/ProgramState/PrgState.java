package model.ProgramState;

import exceptions.InterpreterException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.ADTs.MyIList;
import model.ADTs.MyIStack;
import model.Statements.IStmt;
import model.Values.*;

import java.io.BufferedReader;
import java.util.Map;

public class PrgState {
    private MyIStack<IStmt> exeStack;

    private MyIDictionary<String, Value> symTable;

    private MyIList<Value> out;

    private MyIHeap<Integer, Value> heap;

    private MyIDictionary<StringValue, BufferedReader> fileTable;

    IStmt originalProgram; //optional field, but good to have

    int id;

    private static int nextID = 0;

    public static synchronized int generateNewID(){
        return nextID++;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIDictionary<StringValue, BufferedReader> fileTbl, MyIList<Value>
            ot, MyIHeap<Integer, Value> hp, int id, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = fileTbl;
        heap = hp;
        this.id = id;
        //originalProgram=deepCopy(prg);
        stk.push(prg);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public MyIStack<IStmt> getExecutionStack() {
        return exeStack;
    }

    public void setExecutionStack(MyIStack<IStmt> executionStack) {
        this.exeStack = executionStack;
    }

    public MyIDictionary<String, Value> getSymbolTable() {
        return symTable;
    }

    public void setSymbolTable(MyIDictionary<String, Value> symbolTable) {
        this.symTable = symbolTable;
    }

    public MyIList<Value> getOutput() {
        return out;
    }

    public void setOutput(MyIList<Value> output) {
        this.out = output;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer, Value> heap) {
        this.heap = heap;
    }


    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStepExecution() throws InterpreterException {
        if(exeStack.isEmpty())
            throw new InterpreterException("Program state stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        StringBuilder idString = new StringBuilder();
        StringBuilder exeStackString = new StringBuilder();
        StringBuilder symTableString = new StringBuilder();
        StringBuilder outString = new StringBuilder();
        StringBuilder fileString = new StringBuilder();
        StringBuilder heapString = new StringBuilder();
        idString.append("Id: ");
        idString.append(id);
        idString.append("\n");
        exeStackString.append("ExeStack:\n");
        for (IStmt i : exeStack.get_stack())
            exeStackString.append(i).append("\n");
        symTableString.append("SymTable:\n");
        for(Map.Entry<String, Value> entry : symTable.getAll().entrySet())
        {
            String id = entry.getKey();
            Value v = entry.getValue();
            symTableString.append(id).append("-->").append(v).append("\n");
        }
        outString.append("Out:\n");
        for(Value v: out.getAll())
        {
            outString.append(v).append("\n");
        }
        fileString.append("FileTable:\n");
        for(Map.Entry<StringValue, BufferedReader> entry : fileTable.getAll().entrySet())
        {
            StringValue id = entry.getKey();
            BufferedReader v = entry.getValue();
            fileString.append(id.toString()).append("\n");
        }
        heapString.append("Heap:\n");
        for(Map.Entry<Integer, Value> entry : heap.getAll().entrySet())
        {
            Integer id = entry.getKey();
            Value v = entry.getValue();
            heapString.append(id).append("-->").append(v).append("\n");
        }
        return  "" + idString + exeStackString + symTableString + outString + fileString + heapString;
    }
}
