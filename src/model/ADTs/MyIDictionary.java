package model.ADTs;

import java.util.Map;

public interface MyIDictionary<T1, T2> {
    void put(T1 k, T2 v);
    T2 get(T1 k);
    Map<T1, T2> getAll();
    void remove(T1 k);

    boolean contains_key(T1 k);
    MyIDictionary<T1, T2> copy();
    Map<T1, T2> getContent();
}
