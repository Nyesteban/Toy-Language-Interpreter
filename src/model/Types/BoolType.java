package model.Types;

import model.Values.*;

public class BoolType implements Type{
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool ";
    }
    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
