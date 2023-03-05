package model.Values;

import model.Types.RefType;
import model.Types.Type;

public class RefValue implements Value
{
    int address;
    Type locationType;
    public RefValue(Type locationType)
    {
        this.address = 0;
        this.locationType = locationType;
    }
    public RefValue(int address, Type locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }
    public String toString() {return "("+this.address+","+this.locationType+")";}
    public int getAddr() {return address;}
    public Type getType() { return new RefType(locationType);}
}
