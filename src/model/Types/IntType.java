package model.Types;
import model.Values.*;

public class IntType implements Type{
    public boolean equals(Object another)
    {
        return another instanceof IntType;
    }

    @Override
    public String toString() {
        return "int ";
    }

    @Override
    public Value defaultValue()
    {
        return new IntValue(0);
    }
}
