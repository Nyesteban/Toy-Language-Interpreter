package model.Values;

import model.Types.StringType;
import model.Types.Type;

public class StringValue implements Value{

    private String value;

    public StringValue() {
        this.value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
