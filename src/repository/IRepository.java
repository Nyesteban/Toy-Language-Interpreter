package repository;
import exceptions.InterpreterException;
import model.ProgramState.PrgState;

import java.util.List;

public interface IRepository {
    void addProgramState(PrgState prgState);
    List<PrgState> getProgramList();
    void setProgramList(List<PrgState> prgStateList);
    void logPrgStateExec(PrgState prg) throws InterpreterException;
}
