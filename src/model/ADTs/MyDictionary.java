package model.ADTs;

import java.util.Map;
import java.util.HashMap;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2>{

    private HashMap<T1, T2> dict;

    public MyDictionary() { dict = new HashMap<>(); }

    @Override
    public void put(T1 k, T2 v) { dict.put(k,v); }

    @Override
    public T2 get(T1 k) { return dict.get(k); }

    @Override
    public Map<T1, T2> getAll() { return dict; }

    @Override
    public void remove(T1 k) { dict.remove(k); }

    @Override
    public boolean contains_key(T1 k) {
        return dict.containsKey(k);
    }

    @Override
    public MyIDictionary<T1, T2> copy()
    {
        MyIDictionary<T1, T2> copy_dict = new MyDictionary<>();
        for (T1 key : dict.keySet())
            copy_dict.put(key, dict.get(key));
        return copy_dict;
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.dict;
    }
}
