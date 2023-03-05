package controller;

import exceptions.InterpreterException;
import model.ADTs.MyIHeap;
import model.ADTs.MyIStack;
import model.ProgramState.PrgState;
import model.Statements.IStmt;
import model.Values.RefValue;
import model.Values.Value;
import repository.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController{

    private IRepository repo;

    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addProgramState(PrgState prgState) {
        repo.addProgramState(prgState);
    }

    private Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer>
            heapAddressed, PrgState current){
        MyIHeap<Integer,Value> heap = current.getHeap();
        return heap.getContent().entrySet().stream()
                .filter(elem-> symTableAddr.contains(elem.getKey()) || heapAddressed.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {
                    RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Map<Integer, Value> heap){
        return heap.values().stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue v1 = (RefValue) v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    @Override
    public List<PrgState> removeCompletedProgram(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    /*
    @Override
    public PrgState oneStepExecution(PrgState prgState) throws InterpreterException{
        MyIStack<IStmt> stk = prgState.getExecutionStack();
        if(stk.isEmpty())
            throw new InterpreterException("Program state stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(prgState);
    }*/

    /*
    @Override
    public boolean oneStepExecutionUser() throws InterpreterException {
        PrgState prg = repo.getProgramList().get(0);
        if (!prg.getExecutionStack().isEmpty()) {
            oneStepExecution(prg);
            System.out.println(prg);
            return true;
        }
        else
        {
            for(Value v: prg.getOutput().getAll())
            {
                System.out.println(v);
            }
            System.out.println("end execution");
            repo.getProgramList().remove(0);
            return false;
        }
    }*/

    public void oneStepForAllPrg(List <PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (InterpreterException e) {
                e.printStackTrace();
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStepExecution))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future ->
                {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        System.out.println(e.toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (InterpreterException e) {
                e.printStackTrace();
            }
        });
        repo.setProgramList(prgList);
    }

    /*
    @Override
    public void completeExecution()  throws InterpreterException {
        PrgState prg = repo.getProgramList().get(0); // repo is the controller field of type MyRepoInterface
        //if(displayFlag)
            //System.out.println(prg);
        repo.logPrgStateExec(prg);
        while (!prg.getExecutionStack().isEmpty()) {
            oneStepExecution(prg);
            //if(displayFlag)
                //System.out.println(prg);
            repo.logPrgStateExec(prg);
            prg.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(prg.getSymbolTable().getContent().values()),
                    prg.getHeap().getContent(), getAddrFromHeap(prg.getHeap().getContent())));
            repo.logPrgStateExec(prg);
        }
        for(Value v: prg.getOutput().getAll())
        {
            System.out.println(v);
        }
        repo.getProgramList().remove(0);
    }*/
    @Override
    public void oneStepExecution(){
        // We fix the number of threads
        executor = Executors.newFixedThreadPool(2);
        // We remove the completed programs
        List<PrgState> programs = removeCompletedProgram(this.repo.getProgramList());
        if(programs.size() > 0) {
            Collection<Value> addresses = programs.stream().
                    flatMap(program -> program.getSymbolTable().getContent().values().stream())
                    .collect(Collectors.toList());

            //Collection<ValueInterface> heapAddresses = programs.get(0).getHeap().getContent().values();

            //We apply the safe garbage collector on our heap
            programs.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddrFromSymTable(addresses),
                    this.getAddrFromHeap(programs.get(0).getHeap().getContent()), programs.get(0)));

            try {
                this.oneStepForAllPrg(programs);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            programs = removeCompletedProgram(this.repo.getProgramList());
        }
        executor.shutdown();
        this.repo.setProgramList(programs);
    }
    @Override
    public void completeExecution()  throws InterpreterException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedProgram(repo.getProgramList());
        while(prgList.size() > 0){
            Collection<Value> addresses = prgList.stream().
                    flatMap(program -> program.getSymbolTable().getContent().values().stream())
                    .collect(Collectors.toList());

            //Collection<ValueInterface> heapAddresses = programs.get(0).getHeap().getContent().values();

            //We apply the safe garbage collector on our heap
            prgList.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddrFromSymTable(addresses),
                    this.getAddrFromHeap(prgList.get(0).getHeap().getContent()), prgList.get(0)));
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            //remove the completed programs
            prgList=removeCompletedProgram(repo.getProgramList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setProgramList(prgList);
    }

    @Override
    public IRepository getRepo() {
        return repo;
    }
}
