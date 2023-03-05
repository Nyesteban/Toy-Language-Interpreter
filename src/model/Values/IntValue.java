package model.Values;

import model.Types.*;

public class IntValue implements Value{
    private int value;

    public IntValue() { this.value = 0;}
    public IntValue(int v) { this.value = v;}

    public int getValue() { return this.value;}

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Type getType() {
        return new IntType();
    }
}
