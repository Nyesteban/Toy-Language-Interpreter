package controller;

import exceptions.InterpreterException;
import model.ProgramState.PrgState;
import repository.IRepository;

import java.util.List;

public interface IController {
    void addProgramState(PrgState prgState);
    //PrgState oneStepExecution(PrgState prgState) throws InterpreterException;
    List<PrgState> removeCompletedProgram(List<PrgState> inPrgList);
    void oneStepForAllPrg(List <PrgState> prgList) throws InterruptedException;
    void completeExecution() throws InterpreterException;
    void oneStepExecution();
    IRepository getRepo();
}
