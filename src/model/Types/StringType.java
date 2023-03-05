package model.Types;

import model.Values.StringValue;
import model.Values.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StringType);
    }

    @Override
    public Value defaultValue() {
        return new StringValue();
    }

    @Override
    public String toString() {
        return "String";
    }
}
