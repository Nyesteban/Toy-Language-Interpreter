package view;

import controller.Controller;
import exceptions.InterpreterException;
import model.ADTs.*;
import model.Expressions.*;
import model.ProgramState.PrgState;
import model.Statements.*;
import model.Types.*;
import model.Values.*;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class View {
    /*
    private void exampleExecutor(IStmt ex)
    {
        PrgState state = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), ex);

        System.out.println("Enter the file");
        Scanner fileScanner = new Scanner(System.in);
        String logFilePath = fileScanner.next();

        IRepository repo = new Repository(logFilePath);
        IController controller = new Controller(repo);
        controller.addProgramState(state);

        System.out.println("1 - all steps, 2 - one step");
        Scanner s = new Scanner(System.in);
        while(true) {
            try {
                int n = s.nextInt();
                if (n == 1)
                    try {
                        controller.completeExecution();
                        break;
                    }
                    catch (InterpreterException e)
                    {
                        System.out.println(e.toString());
                    }
                else if (n == 2)
                    try {
                        if(!controller.oneStepExecutionUser())
                            break;
                    }
                    catch (InterpreterException e)
                    {
                        System.out.println(e.toString());
                    }
                else
                    System.out.println("Invalid command!");
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
                s.nextLine();
            }
        }
    }
    public void executeExample1() {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        exampleExecutor(ex1);
    }

    public void executeExample2() {
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)),3),1)),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)),1)), new PrintStmt(new VarExp("b"))))));
        exampleExecutor(ex2);
    }

    public void executeExample3()
    {
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        exampleExecutor(ex3);
    }

    public void chooseAProgram() {
        System.out.println("Choose one of the example programs. Enter the program's number (1 to 3). Enter 0 to exit.");
        Scanner s = new Scanner(System.in);
        while(true) {
            try {
                int n = s.nextInt();
                if (n == 1)
                    executeExample1();
                else if (n == 2)
                    executeExample2();
                else if (n == 3)
                    executeExample3();
                else if (n == 4) {
                    displayFlag = !displayFlag;
                    System.out.println("Display flag value changed!");
                } else if (n == 0)
                    break;
                else
                    System.out.println("Invalid program!");
            }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input!");
                s.nextLine();
            }
        }
    }
    */
    public void chooseAProgram() throws Exception {
        IStmt ex1=new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIDictionary<String, Type> typeEnv1 = new MyDictionary<String, Type>();
        ex1.typeCheck(typeEnv1);
        PrgState prg1 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex1);
        IRepository repo1 = new Repository(prg1,"log1.txt");
        Controller ctr1 = new Controller(repo1);
        IStmt ex2=new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)),3),1)),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)),1)), new PrintStmt(new VarExp("b"))))));
        MyIDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
        ex2.typeCheck(typeEnv2);
        PrgState prg2 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex2);
        IRepository repo2 = new Repository(prg2,"log2.txt");
        Controller ctr2 = new Controller(repo2);
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        MyIDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
        ex3.typeCheck(typeEnv3);
        PrgState prg3 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex3);
        IRepository repo3 = new Repository(prg3,"log3.txt");
        Controller ctr3 = new Controller(repo3);
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()),
                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")),
                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")),
                                        new CloseRFileStmt(new VarExp("varf"))))))))));
        MyIDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
        ex4.typeCheck(typeEnv4);
        PrgState prg4 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex4);
        IRepository repo4 = new Repository(prg4,"log4.txt");
        Controller ctr4 = new Controller(repo4);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(20)),"v"),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt(new VarExp("v"), "a"), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        MyIDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
        ex5.typeCheck(typeEnv5);
        PrgState prg5 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex5);
        IRepository repo5 = new Repository(prg5,"log5.txt");
        Controller ctr5 = new Controller(repo5);
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(20)),"v"),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt(new VarExp("v"), "a"), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new PrintStmt(new ArithExp(new HeapReadExp(new HeapReadExp(new VarExp("a"))), new ValueExp(new IntValue(5)), 1)))))));
        MyIDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
        ex6.typeCheck(typeEnv6);
        PrgState prg6 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex6);
        IRepository repo6 = new Repository(prg6,"log6.txt");
        Controller ctr6 = new Controller(repo6);
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(20)),"v"),
                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new CompStmt(new HeapWriteStmt(new ValueExp(new IntValue(30)), "v"),
                        new PrintStmt(new ArithExp(new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5)), 1))))));
        MyIDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
        ex7.typeCheck(typeEnv7);
        PrgState prg7 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex7);
        IRepository repo7 = new Repository(prg7,"log7.txt");
        Controller ctr7 = new Controller(repo7);
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(20)),"v"),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt(new VarExp("v"), "a"),
                        new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(30)),"v"), new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        MyIDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
        ex8.typeCheck(typeEnv8);
        PrgState prg8 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex8);
        IRepository repo8 = new Repository(prg8,"log8.txt");
        Controller ctr8 = new Controller(repo8);
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),
                        new PrintStmt(new VarExp("v")))));
        MyIDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
        ex9.typeCheck(typeEnv9);
        PrgState prg9 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex9);
        IRepository repo9 = new Repository(prg9,"log9.txt");
        Controller ctr9 = new Controller(repo9);
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocStmt(new ValueExp(new IntValue(22)), "a"),
                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt(new ValueExp(new IntValue(30)), "a"), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
        MyIDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
        ex10.typeCheck(typeEnv10);
        PrgState prg10 =  new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyList<Value>(), new MyHeap<Integer, Value>(), PrgState.generateNewID(), ex10);
        IRepository repo10 = new Repository(prg10,"log10.txt");
        Controller ctr10 = new Controller(repo10);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.addCommand(new RunExample("10",ex10.toString(),ctr10));
        menu.show();
    }
}
