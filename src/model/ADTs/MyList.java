package model.ADTs;
import exceptions.InterpreterException;

import java.util.List;
import java.util.ArrayList;

public class MyList<T> implements MyIList<T>{

    private List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public boolean remove(T t) {
        return list.remove(t);
    }

    @Override
    public T get(int index) throws InterpreterException {
        try {
            return list.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new InterpreterException("Out of bounds!");
        }
    }

    @Override
    public int indexOf(T t) {
        return list.indexOf(t);
    }

    @Override
    public int lastIndexOf(T t) {
        return list.lastIndexOf(t);
    }

    @Override
    public List<T> getAll() {
        return list;
    }
}
