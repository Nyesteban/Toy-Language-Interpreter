package model.ADTs;

import java.util.Map;
import java.util.HashMap;

public class MyHeap<T1, T2> implements MyIHeap<T1, T2>{

    private HashMap<T1, T2> heap;

    int firstPos=1;

    public MyHeap() { heap = new HashMap<>(); }

    @Override
    public void put(T1 k, T2 v) { heap.put(k,v); }

    @Override
    public T2 get(T1 k) { return heap.get(k); }

    @Override
    public Map<T1, T2> getAll() { return heap; }

    @Override
    public void remove(T1 k) { heap.remove(k); }

    @Override
    public void setFirstPos() {
        this.firstPos = this.firstPos + 1;
    }

    @Override
    public int getFirstPos() {
        int posCopy = this.firstPos;
        this.setFirstPos();
        return posCopy;
    }

    @Override
    public boolean contains_key(T1 k) {
        return heap.containsKey(k);
    }

    @Override
    public MyIDictionary<T1, T2> copy()
    {
        MyIDictionary<T1, T2> copy_dict = new MyDictionary<>();
        for (T1 key : heap.keySet())
            copy_dict.put(key, heap.get(key));
        return copy_dict;
    }

    @Override
    public void setContent(Map<T1, T2> newHeap){
        this.heap.clear();
        this.heap.putAll(newHeap);
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.heap;
    }
}
