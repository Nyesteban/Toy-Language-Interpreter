package model.ADTs;
import exceptions.InterpreterException;

import java.util.List;

public interface MyIList<T> {
    void add(T t);
    boolean remove(T t);
    T get(int index) throws InterpreterException;
    int indexOf(T t);
    int lastIndexOf(T t);
    List<T> getAll();
}
