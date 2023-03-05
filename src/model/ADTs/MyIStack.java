package model.ADTs;

import java.util.Stack;
import exceptions.InterpreterException;

public interface MyIStack<T> {

    T pop() throws InterpreterException;
    T get_top() throws InterpreterException;
    void push(T elem);
    boolean isEmpty();
    Stack<T> get_stack();
}
