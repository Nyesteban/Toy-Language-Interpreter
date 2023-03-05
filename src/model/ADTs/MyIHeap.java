package model.ADTs;

import java.util.Map;

public interface MyIHeap<T1, T2> {
    void put(T1 k, T2 v);
    T2 get(T1 k);
    Map<T1, T2> getAll();
    void remove(T1 k);
    public void setFirstPos();
    public int getFirstPos();
    boolean contains_key(T1 k);
    MyIDictionary<T1, T2> copy();
    void setContent(Map<T1, T2> newHeap);
    Map<T1, T2> getContent();
}
