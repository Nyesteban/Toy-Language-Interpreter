package model.ADTs;

import java.util.Stack;
import exceptions.InterpreterException;

public class MyStack<T> implements MyIStack<T>{

    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public T pop() throws InterpreterException {
        if (stack.isEmpty())
            throw new InterpreterException("Stack is empty!");
        return stack.pop();
    }

    @Override
    public T get_top() throws InterpreterException {
        if (stack.isEmpty())
            throw new InterpreterException("Stack is empty!");
        return stack.peek();
    }

    @Override
    public void push(T t) {
        stack.push(t);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public Stack<T> get_stack() {
        return stack;
    }
}
