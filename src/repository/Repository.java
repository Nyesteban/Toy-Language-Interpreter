package repository;

import exceptions.InterpreterException;
import model.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<PrgState> prgStateList;

    private String logFilePath;

    public Repository(String logFilePath) {
        this.prgStateList = new ArrayList<PrgState>();
        this.logFilePath = logFilePath;
    }

    public Repository(PrgState prg, String logFilePath) {
        this.prgStateList = new ArrayList<PrgState>();
        this.logFilePath = logFilePath;
        addProgramState(prg);
    }

    @Override
    public void addProgramState(PrgState prgState) {
        prgStateList.add(prgState);
    }

    @Override
    public List<PrgState> getProgramList() {
        return prgStateList;
    }

    @Override
    public void setProgramList(List<PrgState> prgStateList) {
        this.prgStateList = prgStateList;
    }

    public void logPrgStateExec(PrgState prg) throws InterpreterException
    {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(prg.toString());
            logFile.close();
        }
        catch(IOException e)
        {
            throw new InterpreterException("Error writing the repo into file.");
        }
    }
}
