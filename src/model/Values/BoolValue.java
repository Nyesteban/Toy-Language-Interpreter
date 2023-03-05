package model.Values;

import model.Types.*;

public class BoolValue implements Value{
    private boolean value;

    public BoolValue() { this.value = false;}
    public BoolValue(boolean v) { this.value = v;}

    public boolean getValue() { return this.value;}

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }
}
